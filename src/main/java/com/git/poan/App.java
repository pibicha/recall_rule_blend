package com.git.poan;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    // 使用create_rule_tree.py生成
    private static final String tree = "{\"id\": 127, \"weight\": -1, \"type\": 0, \"son\": [{\"id\": 138, \"weight\": 1, \"type\": 1, \"son\": [{\"id\": 102, \"weight\": -1, \"type\": 0, \"son\": [{\"id\": 3, \"weight\": 3, \"type\": -1, \"son\": null}, {\"id\": 1, \"weight\": 1, \"type\": -1, \"son\": null}, {\"id\": 2, \"weight\": 3, \"type\": -1, \"son\": null}]}, {\"id\": 4, \"weight\": -1, \"type\": -1, \"son\": null}]}, {\"id\": 0, \"weight\": 1, \"type\": -1, \"son\": null}, {\"id\": 185, \"weight\": 1, \"type\": 0, \"son\": [{\"id\": 5, \"weight\": 3, \"type\": -1, \"son\": null}, {\"id\": 6, \"weight\": 1, \"type\": -1, \"son\": null}]}]}";

    public static void main(String[] args) {
        RuleNode ruleNode = JSON.parseObject(tree, RuleNode.class);
        RuleNode merge = MergeNode.merge(ruleNode);
    }
}
