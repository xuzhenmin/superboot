package com.zhenmin.superboot.api.exception;

import com.zhenmin.superboot.Exception.SuperBootException;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public class SuperBootApiException extends SuperBootException {

	public SuperBootApiException(int code, String msg) {
		super(code, msg);
	}

	public SuperBootApiException(int code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
