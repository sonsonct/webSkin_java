package com.WebSkin.demo.models;

public class ResponseOBJ {
	private String status;
	private String message;
	private Object data;
	
	public ResponseOBJ() {
	}

	public ResponseOBJ(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseOBJ [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
