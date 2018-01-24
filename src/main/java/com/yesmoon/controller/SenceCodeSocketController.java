package com.yesmoon.controller;

import com.yesmoon.service.ICameraSocketService;
import com.yesmoon.service.ISenceCodeSocketService;
import com.yesmoon.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听手机侦码的SocketServer
 * SenceCodeSocketController
 * 创建人:夜幕团队
 * 时间：2018年01月24日-下午14:13
 *
 * @version 1.0.0
 */
@Controller
public class SenceCodeSocketController implements ServletContextListener {

    private static ServerSocket serverSocket;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        System.out.println("手机侦码socket启动监听");
        // 监听PC摄像头端的socket请求，创建ServerSocket监听9899端口
        new Thread(new Runnable() {
            ServletContextEvent servletContextEvent1 = servletContextEvent;

            @Override
            public void run() {
                Socket socket = null;
                try {
                    serverSocket = new ServerSocket(Integer.parseInt(PropertiesUtil.getProperty("sencecode.socket.port")));
                    while (true) {
                        socket = serverSocket.accept();
                        ISenceCodeSocketService senceCodeSocketService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent1.getServletContext()).getBean(ISenceCodeSocketService.class);
                        senceCodeSocketService.insertSenceCode(socket);
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
        System.out.println("手机侦码socket关闭监听");
    }
}
