package com.git.poan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.git.poan.bean.RecItem;
import com.git.poan.bean.RuleMergingResult;
import com.git.poan.bean.RuleMergingStrategyEntity;
import com.git.poan.bean.RuleNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleMergingHandler {


    private RuleMergingStrategyEntity ruleMergingStrategyEntity;


    /**
     * 融合主规则集、兜底规则集
     * @param mainRules 主规则集集合
     * @param fallbackRules 兜底规则集集合
     * @return 融合后的结果
     */
    public RuleMergingResult merge(List<RuleNode> mainRules, List<RuleNode> fallbackRules) {


        int mainRuleSize = (int)mainRules.stream().map(rule -> rule.getRecItem().size()).count();
        int fallBackRuleSize = (int)fallbackRules.stream().map(rule -> rule.getRecItem().size()).count();
        Map<Integer, List<RecItem>> idItemMap = mainRules.stream().collect(Collectors.toMap(RuleNode::getId, RuleNode::getRecItem));

        RuleMergingResult ruleMergingResult = new RuleMergingResult();
        List<RecItem> finalMergeItems = new ArrayList<>(mainRuleSize + fallBackRuleSize);

        // 主规则集根据ruleMergingStrategyEntity的expression进行融合(expression是一个树形结构，需要转换为RuleNode)
        String expression = ruleMergingStrategyEntity.getExpression();
        RuleNode rootNode;
        try {
            rootNode = JSON.parseObject(expression, RuleNode.class);
            expression = ruleMergingStrategyEntity.getExpression();
        } catch (JSONException e) {
            return null;//
        }
        RuleNode mainRuleMerged = MergeNode.merge(rootNode, idItemMap);

        finalMergeItems.addAll(mainRuleMerged.getRecItem());
        // 兜底规则集直接进行顺序融合
        List<RecItem> mergedFallbackRules = new ArrayList<>();
        for (RuleNode fallbackRule : fallbackRules) {
            List<RecItem> items = fallbackRule.getRecItem();
            mergedFallbackRules.addAll(items);
        }
        finalMergeItems.addAll(mergedFallbackRules);
        ruleMergingResult.setItems(finalMergeItems);
        return ruleMergingResult;
    }

    public RuleMergingStrategyEntity getRuleMergingStrategyEntity() {
        return ruleMergingStrategyEntity;
    }

    public void setRuleMergingStrategyEntity(RuleMergingStrategyEntity ruleMergingStrategyEntity) {
        this.ruleMergingStrategyEntity = ruleMergingStrategyEntity;
    }
}
