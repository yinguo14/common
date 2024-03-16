package com.military.cloud.common.core.exception;

import com.military.cloud.common.core.response.ResponseEnum;
import com.military.cloud.common.core.response.ServerResponseEntity;

/**
 * @author FrozenWatermelon
 * @date 2020/7/11
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private String code;

	private ResponseEnum responseEnum;

	private ServerResponseEntity<?> serverResponseEntity;

	public BizException(String msg) {
		super(msg);
		this.code = ResponseEnum.UNKNOWN_ERROR.getCode();
	}

	public BizException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public BizException(String msg, Object object) {
		super(msg);
		this.object = object;
		this.code = ResponseEnum.UNKNOWN_ERROR.getCode();
	}

	public BizException(String msg, Throwable cause) {
		super(msg, cause);
		this.code = ResponseEnum.UNKNOWN_ERROR.getCode();
	}


	public BizException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.code = responseEnum.getCode();
	}

	public BizException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.code = responseEnum.getCode();
		this.object = object;
	}

	public BizException(ServerResponseEntity<?> serverResponseEntity) {
		this.serverResponseEntity = serverResponseEntity;
	}

	public ServerResponseEntity<?> getServerResponseEntity() {
		return serverResponseEntity;
	}

	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

	public String getCode() {
		return code;
	}

}
