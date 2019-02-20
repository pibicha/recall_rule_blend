package com.git.poan;

import java.util.List;

/**
 * @Author: panbenxing
 * @Date: 2019/2/19
 * @Description: 规则集节点
 */
public class RuleNode {

    /**
     *  规则集id，如果不含`&`则代表是原始规则集下标
     */
    private String id;

    /**
     * 融合类型，0 ：加权融合（blend）；1 ： 尾部添加（append）；2 ： 由其他部门融合
     */
    private int type;

    private float weight;

    private List<RuleNode> son;

    private List<Rule> rules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<RuleNode> getSon() {
        return son;
    }

    public void setSon(List<RuleNode> son) {
        this.son = son;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "RuleNode{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", son=" + son +
                '}';
    }
}
