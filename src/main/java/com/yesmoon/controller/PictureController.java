package com.yesmoon.controller;

import com.yesmoon.service.PictureService;
import com.yesmoon.util.JsonUtils;
import com.yesmoon.vo.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传controller
 * @author cool
 *
 */
@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/file/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile) { // 这里的uploadFile要和前端jsp页面中的参数一致 
		PictureResult result = pictureService.uploadPicture(uploadFile);
		return JsonUtils.objectToJson(result);
	}
	
}
