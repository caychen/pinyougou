package com.pinyougou.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Author:       Caychen
 * Enum:        com.pinyougou.enums.MsgEnum
 * Date:         2019/1/5
 * Desc:
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum MsgEnum {

    ADD_SUCCESS("添加成功"),
    ADD_FAILED("添加失败"),

    UPDATE_SUCCESS("更新成功"),
    UPDATE_FAILED("更新失败"),

    DELETE_SUCCESS("删除成功"),
    DELETE_FAILED("删除失败"),
    ;

    private String msg;

    public String getMsg() {
        return msg;
    }
}
