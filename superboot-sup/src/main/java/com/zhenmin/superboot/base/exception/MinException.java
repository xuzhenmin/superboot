package com.zhenmin.superboot.base.exception;

/**
 * Created by xuzhenmin on 17-5-31.
 */
public class MinException extends Exception {

	private int code;

	public MinException(String msg) {
		super(msg);
	}

	public MinException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

	public MinException(int code) {
		super();
		this.code = code;
	}

	public MinException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public MinException(int code, String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
		this.code = code;
	}

	public MinException(int code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}