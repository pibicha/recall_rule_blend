package com.git.poan.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: panbenxing
 * @Date: 2019/2/20
 * @Description:
 */
public enum RuleMixTypeEnum {

    /**
     * （交叉）加权融合
     */
    BLEND(0),
    /**
     * 顺序融合
     */
    APPEND(1),

    /**
     * 接口融合
     */
    JSF(2)
    //
    ;

    private final int val;
    RuleMixTypeEnum(int val) {
        this.val = val;
    }

    private static final Map<Integer, RuleMixTypeEnum> cacheMap = new HashMap(RuleMixTypeEnum.values().length);

    static {
        for (RuleMixTypeEnum ruleMixTypeEnum : RuleMixTypeEnum.values()) {
            cacheMap.put(ruleMixTypeEnum.val,ruleMixTypeEnum);
        }
    }

    public static RuleMixTypeEnum getByVal(int type){
        return cacheMap.get(type);
    }

}
