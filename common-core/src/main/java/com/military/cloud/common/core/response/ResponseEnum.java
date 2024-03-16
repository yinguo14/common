package com.military.cloud.common.core.response;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    OK("00000", "成功"),

    METHOD_ARGUMENT_NOT_VALID("400", "请求参数异常"),

    UNAUTHORIZED("401", "未认证"),

    NO_PERMISSION("403", "未授权"),

    METHOD_NOT_ALLOWED("405", "方法不允许"),

    ARGUMENTS_NOT_VALID("406", "参数未通过校验"),

    VERIFICATION_CODE_ERROR("407", "验证码有误或已过期"),

    LOGIN_STATE_EXPIRED("408", "登录状态已失效，请重新登录"),

    LOGIN_STATE_RENEWAL_FAILED("409", "登录状态续期失败，请重新登录"),

    SENSITIVE_WORD("410", "存在敏感词，请重新输入"),

    SIGNATURE_VERIFICATION_FAILED("411", "签名验证失败"),

    ARGUMENTS_MISSING("412", "请求参数缺失"),

    HTTP_MESSAGE_NOT_READABLE("413", "消息不可读"),

    DUPLICATE_DATA("414", "存在重复数据"),

    INTERNAL_SERVER_ERROR("500", "服务内部异常"),

    SERVER_NOT_TOUCHABLE("501", "服务不可用"),

    SERVER_TIMEOUT("503", "服务响应超时"),

    UNKNOWN_ERROR("999", "未知异常"),

    EXCEPTION("A00001", "服务器出了点小差"),
    ;

    private final String code;

    private final String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "} " + super.toString();
    }

}
