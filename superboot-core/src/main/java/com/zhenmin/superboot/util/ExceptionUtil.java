package com.zhenmin.superboot.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by xuzhenmin on 17-6-5.
 */
public abstract class ExceptionUtil {

	public static String getStackTraceInfo(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
