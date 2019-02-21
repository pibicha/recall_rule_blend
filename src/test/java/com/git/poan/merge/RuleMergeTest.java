package com.git.poan.merge;

import com.alibaba.fastjson.JSON;
import com.git.poan.RuleMergingHandler;
import com.git.poan.bean.RuleMergingStrategyEntity;
import com.git.poan.bean.RuleNode;
import com.git.poan.bean.RecItem;
import com.git.poan.bean.RuleMergingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: panbenxing
 * @Date: 2019/2/21
 * @Description:
 */
public class RuleMergeTest {
    public static void main(String[] args) {

        List<RuleNode> mainRules = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RuleNode recRule = new RuleNode();
            recRule.setId(i);
            ArrayList<RecItem> recItems = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                RecItem recItem = new RecItem();
                recItem.setId("" + i + j + 1);
                recItems.add(recItem);
            }
            recRule.setRecItem(recItems);
            mainRules.add(recRule);
        }
        System.out.println(JSON.toJSONString(mainRules));
        System.out.println("____________融合前後_____________");


        RuleMergingHandler ruleMergingHandler = new RuleMergingHandler();
        String expression = "{\"id\": 135, \"weight\": -1, \"type\": 1, \"son\": [{\"id\": 7, \"weight\": -1, \"type\": -1, \"son\": null}, {\"id\": 116, \"weight\": -1, \"type\": 1, \"son\": [{\"id\": 154, \"weight\": -1, \"type\": 0, \"son\": [{\"id\": 5, \"weight\": 1, \"type\": -1, \"son\": null}, {\"id\": 1, \"weight\": 2, \"type\": -1, \"son\": null}, {\"id\": 3, \"weight\": 3, \"type\": -1, \"son\": null}]}, {\"id\": 0, \"weight\": -1, \"type\": -1, \"son\": null}]}, {\"id\": 143, \"weight\": -1, \"type\": 0, \"son\": [{\"id\": 164, \"weight\": 8, \"type\": 0, \"son\": [{\"id\": 2, \"weight\": 1, \"type\": -1, \"son\": null}, {\"id\": 4, \"weight\": 1, \"type\": -1, \"son\": null}, {\"id\": 8, \"weight\": 1, \"type\": -1, \"son\": null}]}, {\"id\": 9, \"weight\": 3, \"type\": -1, \"son\": null}, {\"id\": 6, \"weight\": 5, \"type\": -1, \"son\": null}]}]}";
        RuleMergingStrategyEntity ruleMergingStrategyEntity = new RuleMergingStrategyEntity();
        ruleMergingStrategyEntity.setExpression(expression);
        ruleMergingHandler.setRuleMergingStrategyEntity(ruleMergingStrategyEntity);
        RuleMergingResult merge = ruleMergingHandler.merge(mainRules, Collections.emptyList());
        System.out.println(JSON.toJSONString(merge));

    }
}
