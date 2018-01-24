package com.yesmoon.controller;

import com.yesmoon.common.ResponseCode;
import com.yesmoon.pojo.SenceCode;
import com.yesmoon.service.ISenceCodeService;
import com.yesmoon.service.IUserService;
import com.yesmoon.util.YmResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SenceCodeController
 * 创建人:夜幕团队
 * 时间：2018年01月23日-下午19:24
 *
 * @version 1.0.0
 */
@Controller
public class SenceCodeController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISenceCodeService iSenceCodeService;

    @RequestMapping(value = "/getcode", method = RequestMethod.POST)
    @ResponseBody
    public YmResult selectSenceCode(String username, String token){
        // 校验用户是否登录
        int resultCount = iUserService.checkLoginState(username, token);
        if (resultCount == 0){
            return YmResult.build(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，无权限读取手机侦码");
        }
        // 获取手机侦码
        SenceCode senceCode = iSenceCodeService.getSenceCode();
        if (senceCode == null){
            return YmResult.build(ResponseCode.ERROR.getCode(),"获取侦码数据失败，没有侦码数据的记录");
        }
        return YmResult.ok(senceCode);
    }
}
