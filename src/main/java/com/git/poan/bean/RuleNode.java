package com.git.poan.bean;

import java.util.List;

/**
 * @Author: panbenxing
 * @Date: 2019/2/19
 * @Description: 规则集节点
 */
public class RuleNode {

    private Integer id;

    /**
     * 融合类型，0 ：加权融合（blend）；1 ： 顺序融合（append）；2 ： 由其他部门融合
     */
    private int type;

    private int weight;

    private List<RuleNode> son;

    private List<RecItem> recItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<RuleNode> getSon() {
        return son;
    }

    public void setSon(List<RuleNode> son) {
        this.son = son;
    }

    public List<RecItem> getRecItem() {
        return recItem;
    }

    public void setRecItem(List<RecItem> recItem) {
        this.recItem = recItem;
    }
}
