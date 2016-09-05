package org.seckill.enums;

/**
 * Created by zfx on 2016/8/31.
 * 使用枚举表示常量数据字段
 */
public enum SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;//状态
    private String stateInfo;//状态的描述

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    public static SeckillStatEnum stateOf(int index){
        for(SeckillStatEnum state :values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
