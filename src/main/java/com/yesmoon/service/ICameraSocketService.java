package com.yesmoon.service;

import com.yesmoon.pojo.Camera;
import com.yesmoon.pojo.User;

import java.io.IOException;
import java.net.Socket;

/**
 * ICameraSocketService
 * 创建人:夜幕团队
 * 时间：2018年01月24日-下午10:52
 *
 * @version 1.0.0
 */
public interface ICameraSocketService {

    Camera getCameraByUsernameAndCid(String username, String cid);

    void registerCamera(Socket socket) throws IOException;

    void transMessage(String ip, Integer port, String message) throws IOException;
}
