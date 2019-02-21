package com.git.poan.bean;

import java.util.Map;

public class RecItem {

    /**
     * 物品id
     */
    private String id;

    /**
     * 埋点信息
     */
    private String tracking;

    public RecItem() {
    }

    public RecItem(String id, String tracking) {
        this.id = id;
        this.tracking = tracking;
    }

    /**
     * 扩展字段
     */
    private Map<String, Object> ext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if(obj instanceof RecItem) {
            RecItem i = (RecItem)obj;
            if(this.getId().equals(i.getId())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
