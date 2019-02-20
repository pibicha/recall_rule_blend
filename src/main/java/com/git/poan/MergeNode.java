package com.git.poan;

import java.util.List;

/**
 * @Author: panbenxing
 * @Date: 2019/2/20
 * @Description:
 */
public class MergeNode {

    public static RuleNode merge(RuleNode ruleNode) {
        int type = ruleNode.getType();
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
            // todo 根据type 对son进行融合;然后返回融合后的规则集;set到当前节点的rules属性
            System.out.println("id[" + ruleNode.getId() + "]的子节点都是叶子节点，先对该node进行融合");
            List<RuleNode> nodeSon = ruleNode.getSon();
            for (RuleNode node : nodeSon) {
                System.out.println("id[" + node.getId() + "]融合中.. 权重为-" + node.getWeight());
            }
            System.out.println("[" + ruleNode.getId() + "]节点融合完成.");
            //fixme 当该节点融合完成以后，将该节点的子孙去除，避免无限循环——remove this node's child
            ruleNode.setSon(null);
            return ruleNode;
        }
        // 情况2:部分节点是部分的son有自己的子孙节点——递归对该节点进行操作
        for (int i = 0; i < son.size(); i++) {
            RuleNode node = son.get(i);
            if (node.getSon()!=null && node.getSon().size() != 0){
                son.set(i, merge(node));
            }
        }
        // 在经历情况1、2后，再进行一次递归，即可完成树的融合
        return merge(ruleNode);
    }

}
