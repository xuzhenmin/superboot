package com.zhenmin.superboot.Exception;

/**
 * Created by xuzhenmin on 17-5-31.
 */
public class SuperBootException extends Exception {

	private int code;

	public SuperBootException(String msg) {
		super(msg);
	}

	public SuperBootException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

	public SuperBootException(int code) {
		super();
		this.code = code;
	}

	public SuperBootException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public SuperBootException(int code, String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
		this.code = code;
	}

	public SuperBootException(int code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}