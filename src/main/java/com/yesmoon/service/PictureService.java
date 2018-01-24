package com.yesmoon.service;

import com.yesmoon.vo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片处理
 * @author cool
 *
 */
public interface PictureService {

	PictureResult uploadPicture(MultipartFile uploadFile);
}
