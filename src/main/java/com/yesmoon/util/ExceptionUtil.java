package com.yesmoon.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * 
 * ExceptionUtil
 * 创建人:夜幕团队 
 * 时间：2018年1月22日-下午5:49:41 
 * @version 1.0.0
 *
 */
public class ExceptionUtil {

	/**
	 * 获取异常的堆栈信息
	 * 方法名：getStackTrace
	 * 创建人：夜幕团队 
	 * 时间：2018年1月22日-下午5:49:35 
	 * 手机:18850149410
	 * @param t
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getStackTrace(Throwable t) {
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
