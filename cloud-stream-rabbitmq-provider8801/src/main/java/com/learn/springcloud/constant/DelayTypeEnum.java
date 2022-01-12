package com.learn.springcloud.constant;

/**
 * @ClassName DelayTypeEnum
 * @Description TODO
 * @Author lin
 * @Date 2022/1/12
 * @Version 1.0
 */
public enum DelayTypeEnum {

    DELAY_10s(1, "延时10s"),
    DELAY_60s(2, "延时60s");

    private Integer code;
    private String desc;

    DelayTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DelayTypeEnum getDelayTypeEnumByValue(Integer code){
        for (DelayTypeEnum value : DelayTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}
