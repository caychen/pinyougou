package com.pinyougou;

import org.apache.commons.lang3.StringUtils;

/**
 * Author:       Caychen
 * Enum:        com.pinyougou.StatusEnum
 * Date:         2018/10/28
 * Desc:
 */

public enum StatusEnum {
    WAITING("0", "待审核"),
    SUCCESS("1", "审核通过"),
    FAILED("2", "审核未通过"),
    CLOSED("3", "关闭商家");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum getDescByCode(String code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
}
