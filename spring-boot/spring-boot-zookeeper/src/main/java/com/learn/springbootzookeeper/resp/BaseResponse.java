package com.learn.springbootzookeeper.resp;




import java.io.Serializable;

public class BaseResponse<T> implements Serializable{
	private String code;
	
	private String message;

	/**
	 * 请求号
	 */
	private String reqNo;
	
	private T dataBody;

	public BaseResponse() {}

	public BaseResponse(T dataBody) {
		this.dataBody = dataBody;
	}

	public BaseResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResponse(String code, String message, T dataBody) {
		this.code = code;
		this.message = message;
		this.dataBody = dataBody;
	}

	public BaseResponse(String code, String message, String reqNo, T dataBody) {
		this.code = code;
		this.message = message;
		this.reqNo = reqNo;
		this.dataBody = dataBody;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public T getDataBody() {
		return dataBody;
	}

	public void setDataBody(T dataBody) {
		this.dataBody = dataBody;
	}
}
