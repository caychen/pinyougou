package com.pinyougou.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author:       Caychen
 * Class:        com.pinyougou.result.JsonResult
 * Date:         2018/10/3
 * Desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult implements Serializable {

    private boolean success;

    private String message;

    private Object data;

    /**
     * 正常：无需返回数据
     *
     * @return
     */
    public static JsonResult ok(){
        return JsonResult.ok(null);
    }

    /**
     * 正常：返回消息
     *
     * @param msg
     * @return
     */
    public static JsonResult ok(String msg){
        return JsonResult.ok(msg, null);
    }

    /**
     * 正常：需要返回数据
     *
     * @param res   需要被返回的数据
     * @return
     */
    public static JsonResult ok(Object res){
        return new JsonResult(true, "", res);
    }

    /**
     * 正常：返回数据及消息
     *
     * @param msg
     * @param res
     * @return
     */
    public static JsonResult ok(String msg, Object res){
        return new JsonResult(true, msg, res);
    }

    /**
     * 错误：无需返回错误消息
     *
     * @return
     */
    public static JsonResult fail(){
        return JsonResult.fail(null);
    }

    /**
     * 错误：需要返回错误消息
     *
     * @param msg   错误消息
     * @return
     */
    public static JsonResult fail(String msg){
        return new JsonResult(false, msg, null);
    }
}
