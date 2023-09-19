package com.example.common.cloud.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Result
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/17 22:26   @Version 1.0        描述
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("返回码")
    private int resultCode;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public Result() {

    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public static <T> Result ok() {
        Result result = new Result();
        result.resultCode = 200;
        result.message = "Success";
        return result;
    }

    public static <T> Result ok(T data) {
        Result result = new Result();
        result.resultCode = 200;
        result.message = "Success";
        result.data = data;
        return result;
    }

    public static <T> Result ok(int resultCode, String message, T data) {
        Result result = new Result();
        result.resultCode = resultCode;
        result.message = message;
        result.data = data;
        return result;
    }

    public static <T> Result error() { return error(500, "未知异常,请联系管理员"); }

    public static <T> Result error(String msg) { return error(500, msg); }

    public static <T> Result error(int code, String msg) {
        Result result = new Result();
        result.resultCode = code;
        result.message = msg;
        return result;
    }
}
