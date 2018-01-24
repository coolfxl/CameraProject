package com.yesmoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yesmoon.common.ResponseCode;
import com.yesmoon.pojo.User;
import com.yesmoon.service.IUserService;
import com.yesmoon.util.YmResult;

@Controller
public class UserController {

    @Value("${RTMP_SERVER_BASE_URM}")
    private String RTMP_SERVER_BASE_URM;

    @Autowired
    private IUserService iUserService;

    /**
     * 登录
     * 方法名：login
     * 创建人：夜幕团队
     * 时间：2018/1/22-19:03
     * 手机:15859518203
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public YmResult login(String username, String password) {
        return iUserService.login(username, password);
    }

    /**
     * 退出登录
     * 方法名：logout
     * 创建人：夜幕团队
     * 时间：2018/1/23-16:33
     * 手机:15859518203
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public YmResult logout(String username) {
        iUserService.logout(username);
        return YmResult.ok();
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public YmResult register(User user) {
        return iUserService.register(user);
    }

    /**
     * 返回cid对应的摄像头的视频流地址
     * @param username
     * @param token
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getvideo", method = RequestMethod.POST)
    @ResponseBody
    public YmResult getVideo(String username, String token, String cid){
        int resultCount = iUserService.checkLoginState(username, token);
        if (resultCount == 0) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "用户未登录，无权监控摄像头");
        }
        //TODO: 返回该用户对应的视频流地址，这里可能需要验证摄像头的cid是否属于该用户(查询camera表)，不属于返回用户未拥有摄像头，无权限操作
        return YmResult.ok(RTMP_SERVER_BASE_URM);
    }
}
