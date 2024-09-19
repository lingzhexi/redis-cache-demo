package com.codeling.rediscache.utils;

import com.codeling.rediscache.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    // 操作代码
    Integer code;

    // 提示信息
    String message;

    // 结果数据
    T data;

    public Result(ResultEnum resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(ResultEnum resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }
    public Result(String message) {
        this.message = message;
    }
    //成功返回封装-无数据
    public static Result<String> success() {
        return new Result<String>(ResultEnum.SUCCESS);
    }
    //成功返回封装-带数据
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS, data);
    }
    //失败返回封装-使用默认提示信息
    public static Result<String> error() {
        return new Result<String>(ResultEnum.FAIL);
    }
    //失败返回封装-使用返回结果枚举提示信息
    public static Result<String> error(ResultEnum resultCode) {
        return new Result<String>(resultCode);
    }
    //失败返回封装-使用自定义提示信息
    public static Result<String> error(String message) {
        return new Result<String>(message);

    }
}
