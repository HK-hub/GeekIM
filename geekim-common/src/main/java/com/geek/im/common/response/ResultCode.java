package com.geek.im.common.response;

public enum ResultCode {

    // 成功请求
    SUCCESS(true ,200, "success"),
    //已创建  请求成功并且服务器创建了新的资源
    SUCCESS_CREATE(true ,201,"success create resource"),
    //请求执行成功, 但是没有数据返回
    SUCCESS_NO_CONTENT(true,204,"success and no data response"),
    // 重定向
    REDIRECT(true,301, "redirect"),
    //请求语法错误，请求参数错误
    BAD_REQUEST(false, 400 ,"request parameters error"),
    // 不合法的 请求头
    ILLEGAL_HEADER(false, 40002, "illegal request header"),
    //未授权
    UNAUTHORIZED(false,401,"unauthorized"),
    //未认证
    UNAUTHENTICATED(false, 102, "unauthenticated") ,
    USER_HAS_EXITS(false,104,"user has exits"),
    NO_SUCH_USER(false, 105, "no such user"),
    // 不存在此资源
    NO_SUCH_RESOURCE(false, 1106, "no such resource"),
    //资源禁止访问, 可以用来控制权限
    FORBIDDEN(false,403,"no permission"),
    // 资源未找到
    NOT_FOUND(false,404, "not found"),
    //账号或者密码错误
    ACCOUNT_PASSWORDS_ERROR(false,101,"account or password error"),
    //accessToken 校验不合法
    TOKEN_ERROR(false,108,"accessToken is illegal or invalid"),
    TOKEN_INVALIDATE(false, 107, "token is invalid, you need login again"),

    REMOTE_INTERFACE_ERROR(false,700, "接口调用异常"),
    // 服务器错误
    SERVER_ERROR(false,500,"server error"),
    SERVER_BUSY(false,999,"服务繁忙,请稍后重试"),
    //请求失败
    FAIL(false,-100,"fail"),
    //上传失败，上传文件不能为空
    SC_EXPECTATION_FAILED(false,-110,"file is empty"),
    CHECK_CODE_SEND_FAILED(false, 103,"user check code send fail"),
    NO_SUPPORT_OPERATION(false, 106, "no support operation"),
    NO_PERMISSION(false, 109 , "no permission"),;



    //---用户操作返回码----
    //---企业操作返回码----
    //---权限操作返回码----
    //---其他操作返回码----

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }


}
