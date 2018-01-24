package com.yesmoon.service.impl;

import com.yesmoon.service.PictureService;
import com.yesmoon.util.FastDFSClient;
import com.yesmoon.vo.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件处理服务实现类
 * 
 * @author cool
 *
 */
@Service
public class PictureServiceImpl implements PictureService {
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;

	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile) {
		// 判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			return PictureResult.error("上传图片为空");
		}

		// 取文件扩展名
		String originalFilename = uploadFile.getOriginalFilename();
		String ext = originalFilename.substring(originalFilename
				.lastIndexOf("."));
		// 拼接图片服务器地址
		String url = "";
		// 上传到图片让服务器
		try {
			FastDFSClient client = new FastDFSClient(
					"classpath:properties/client.conf");
			url = IMAGE_SERVER_BASE_URL + client.uploadFile(uploadFile.getBytes(), ext);
		} catch (Exception e) {
			e.printStackTrace();
			return PictureResult.error("文件上传失败");
		}
		return PictureResult.ok(url);
	}

}
