package com.geek.im.common.response;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据响应对象
 * {
 * success：成功,
 * code   ：响应码,
 * message:返回信息,
 * //响应数据
 * data:{
 * <p>
 * }
 * <p>
 * }
 *
 * @author HK意境
 **/

@Data
@Accessors(chain = true)
@ToString
public class ResponseResult<T> {


    // 静态返回对象
    public static ResponseResult SuccessResponse = new ResponseResult(ResultCode.SUCCESS, "ok");
    public static ResponseResult FailResponse = new ResponseResult(ResultCode.FAIL, "failed");
    public static ResponseResult ErrorResponse = new ResponseResult(ResultCode.REMOTE_INTERFACE_ERROR, "exception");
    // 是否成功
    private boolean success;
    // 链路追踪
    private String traceId;
    // 返回码
    private Integer code;
    // 返回信息
    private String message;
    /**
     * 错误建议
     */
    private String suggestion;
    // 返回数据
    private T data;
    // 响应时间
    private LocalDateTime dateTime = LocalDateTime.now();
    private ResultEntity result;


    public ResponseResult() {

        this.dateTime = LocalDateTime.now();
    }

    public ResponseResult(ResultCode code) {
        this(code.code(), code.message(), code.success());
    }

    public ResponseResult(ResultCode code, T data) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
        this.data = data;
        this.dateTime = LocalDateTime.now();
    }

    public ResponseResult(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.dateTime = LocalDateTime.now();
    }

    // 构建成功响应对象
    public static <T> ResponseResult<T> SUCCESS() {
        return new ResponseResult<T>(ResultCode.SUCCESS);
    }

    public static <T> ResponseResult<T> SUCCESS(T data) {
        return new ResponseResult<T>(ResultCode.SUCCESS, data);
    }

    public static <T> ResponseResult<T> SUCCESS(T data, String message) {

        ResponseResult<T> response = new ResponseResult<>();
        response.setData(data).setResultCode(ResultCode.SUCCESS).setMessage(message);
        return response;
    }

    public static <T> ResponseResult<T> SUCCESS(T data, Integer code, String message) {

        ResponseResult<T> response = new ResponseResult<>();
        response.setData(data).setSuccess(true)
                .setCode(code)
                .setMessage(message);
        return response;
    }

    // 构建错误异常响应对象
    public static ResponseResult<?> ERROR() {
        return new ResponseResult<>(ResultCode.SERVER_ERROR);
    }

    public static ResponseResult<Exception> ERROR(Exception exception) {

        ResponseResult<Exception> result = new ResponseResult<>();
        result.setResultCode(ResultCode.SERVER_BUSY)
                .setMessage(exception.getMessage())
                .setData(exception);

        return result;
    }

    // 构建失败响应对象
    public static <T> ResponseResult<T> FAIL() {
        return new ResponseResult<T>(ResultCode.FAIL);
    }

    public static <T> ResponseResult<T> FAIL(T data) {
        return new ResponseResult<T>(ResultCode.FAIL, data);
    }

    public static <T> ResponseResult<T> FAIL(ResultCode resultCode) {
        return new ResponseResult<T>(resultCode);
    }

    public static <T> ResponseResult<T> FAIL(T data, String message) {

        ResponseResult<T> response = new ResponseResult<>();
        response.setData(data).setResultCode(ResultCode.FAIL).setMessage(message);
        return response;
    }

    public static <T> ResponseResult<T> FAIL(T data, Integer code, String message) {

        ResponseResult<T> response = new ResponseResult<>();
        response.setData(data).setSuccess(false)
                .setCode(code)
                .setMessage(message);
        return response;
    }

    public ResponseResult<T> setResultCode(ResultCode code) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
        return this;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 设置响应数据并且同时设置响应消息
     *
     * @param data
     *
     * @return
     */
    public ResponseResult<T> setDataAsMessage(T data) {
        this.data = data;
        this.message = data.toString();
        return this;
    }

    public ResponseResult<T> setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

}

