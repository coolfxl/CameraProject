package com.yesmoon.controller;

import com.yesmoon.common.ResponseCode;
import com.yesmoon.pojo.Camera;
import com.yesmoon.service.ICameraSocketService;
import com.yesmoon.service.IUserService;
import com.yesmoon.util.PropertiesUtil;
import com.yesmoon.util.YmResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CameraController
 * 创建人:夜幕团队
 * 时间：2018年01月24日-下午10:45
 *
 * @version 1.0.0
 */
@Controller
public class CameraController implements ServletContextListener {

    @Autowired
    private ICameraSocketService iCameraSocketService;

    @Autowired
    private IUserService iUserService;

    private static ServerSocket serverSocket;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        System.out.println("摄像头socket启动监听");
        // 监听PC摄像头端的socket请求，创建ServerSocket监听10010端口
        new Thread(new Runnable() {
            ServletContextEvent servletContextEvent1 = servletContextEvent;

            @Override
            public void run() {
                Socket socket = null;
                try {
                    serverSocket = new ServerSocket(Integer.parseInt(PropertiesUtil.getProperty("camera.socket.port")));
                    while (true) {
                        socket = serverSocket.accept();
                        ICameraSocketService socketService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent1.getServletContext()).getBean(ICameraSocketService.class);
                        socketService.registerCamera(socket);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("摄像头socket关闭监听");
    }


    /**
     * 转发Android客户端的摄像头控制请求，通过socket
     * 方法名：ctrl
     * 创建人：夜幕团队
     * 时间：2018/1/24-13:08
     * 手机:15859518203
     *
     * @param username
     * @param token
     * @param message
     * @return
     */
    @RequestMapping(value = "ctrl", method = RequestMethod.POST)
    @ResponseBody
    public YmResult ctrl(String username, String token, String cid, String message) {
        int resultCount = iUserService.checkLoginState(username, token);
        if (resultCount == 0) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "用户未登录，无权限操作摄像头，请登录后操作");
        }
        Camera camera = iCameraSocketService.getCameraByUsernameAndCid(username, cid);
        if (camera == null) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "该用户与摄像头不匹配，无权限操作，请检查摄像头是否注册或者用户是否拥有该摄像头");
        }
        try {
            iCameraSocketService.transMessage(camera.getIp(), camera.getPort(), message);
        } catch (IOException e) {
            return YmResult.build(ResponseCode.ERROR.getCode(),"指令转发失败，请检查摄像头PC是否开启socket服务");
        }
        return YmResult.ok("指令转发成功");
    }


}
