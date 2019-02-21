package com.git.poan;

import com.git.poan.bean.RecItem;
import com.git.poan.bean.RuleMixTypeEnum;
import com.git.poan.bean.RuleNode;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: panbenxing
 * @Date: 2019/2/20
 * @Description:
 */
public class MergeNode {

    public static RuleNode merge(RuleNode ruleNode, Map<Integer, List<RecItem>> idItemMap) {

        List<RuleNode> son = ruleNode.getSon();
        // 递归终止条件 ： 如果该节点就是一个叶子节点；或者已经融合完毕，直接返回自身
        if (son == null || son.size() == 0) {
            return ruleNode;
        }
        // 情况1:所有子节点都是叶子节点，直接进行融合
        boolean allLeaf = true;
        for (RuleNode node : son) {
            if (node.getSon()!=null && node.getSon().size() != 0){
                allLeaf = false;
            }
        }
        if (allLeaf) {
            //  根据type 对son进行融合;然后返回融合后的规则集;set到当前节点的rules属性
            List<RecItem> mix = mix(ruleNode, idItemMap);
            idItemMap.putIfAbsent(ruleNode.getId(), mix);
            ruleNode.setRecItem(mix);
            // 当该节点融合完成以后，将该节点的子孙去除，避免无限循环——remove this node's child
            ruleNode.setSon(null);
            return ruleNode;
        }
        // 情况2:部分节点是部分的son有自己的子孙节点——递归对该节点进行操作
        for (int i = 0; i < son.size(); i++) {
            RuleNode node = son.get(i);
            if (node.getSon()!=null && node.getSon().size() != 0){
                son.set(i, merge(node, idItemMap));
            }
        }
        // 在经历情况1、2后，再进行一次递归，即可完成树的融合
        return merge(ruleNode, idItemMap);
    }

    private static List<RecItem> mix(RuleNode ruleNode, Map<Integer, List<RecItem>> idItemMap) {
        int type = ruleNode.getType();

        List<RuleNode> son = ruleNode.getSon();
        RuleMixTypeEnum ruleMixTypeEnum = RuleMixTypeEnum.getByVal(type);
        int allRecItem = (int)son.stream().map(node -> idItemMap.get(node.getId()).size()).count();
        List<RecItem> recItems = new ArrayList<>(allRecItem);

        switch (ruleMixTypeEnum){
            case BLEND:
                List<List<List<RecItem>>> partions = new ArrayList<>(son.size());
                // 使用guava工具类将各个子节点的推荐物料进行切割
                for (int i = 0; i < son.size(); i++) {
                    RuleNode currNode = son.get(i);
                    List<RecItem> currItem = idItemMap.get(currNode.getId());
                    List<List<RecItem>> partition = Lists.partition(currItem, currNode.getWeight());
                    partions.add(partition);
                }
                int loop = 0;
                while (partions.size() != 0) {
                    for (int i = 0; i < partions.size(); i++) {
                        List<List<RecItem>> partition = partions.get(i);
                        if (loop < partition.size()) {
                            List<RecItem> recItemList = partition.get(loop);
                            recItems.addAll(recItemList);
                        } else {
                            partions.remove(partition);
                        }
                    }
                    loop++;
                }
                // 创建son.size个指针，分别记录每个son的偏移量
                break;
            case APPEND:
                for (RuleNode node : son) {
                    recItems.addAll(idItemMap.get(node.getId()));
                }
                break;
            case JSF:
                break;
            default:
        }
        //todo 根据type对子节点进行融合
        return recItems;
    }

}
