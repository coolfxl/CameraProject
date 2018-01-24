package com.yesmoon.vo;

/**
 * 上传图片的返回结果
 * 
 * @author cool
 *
 */
public class PictureResult {

	private int error; // 成功或者失败的标识符 0代表成功，1代表失败
	private String url; // 成功后返回的url地址
	private String message; // 返回的消息

	private PictureResult(int error, String url, String message) {
		this.error = error;
		this.url = url;
		this.message = message;
	}

	// 成功时调用的方法
	public static PictureResult ok(String url) {
		return new PictureResult(0, url, null);
	}

	// 失败时调用的方法
	public static PictureResult error(String message) {
		return new PictureResult(1, null, message);
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
