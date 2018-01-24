package com.yesmoon.service.impl;

import com.yesmoon.common.Const;
import com.yesmoon.dao.CameraMapper;
import com.yesmoon.dao.UserMapper;
import com.yesmoon.pojo.Camera;
import com.yesmoon.pojo.User;
import com.yesmoon.service.ICameraSocketService;
import com.yesmoon.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

@Service
public class CameraSocketServiceImpl implements ICameraSocketService {

    //TODO: userMapper和cameraMapper调用时为null
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CameraMapper cameraMapper;

    /**
     * 向指定IP转发消息，通过Socket
     * 方法名：transMessage
     * 创建人：夜幕团队
     * 时间：2018/1/22-21:46
     *
     * @param ip
     * @param message
     */
    @Override
    public void transMessage(String ip, Integer port, String message) throws IOException {
        System.out.println("发送数据: " + ip + ":" + port + " [" + message + "]");
        Socket socket = new Socket(ip, port);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.write(message);
        pw.flush();
        socket.close();
    }


    @Override
    public Camera getCameraByUsernameAndCid(String username, String cid) {
        return cameraMapper.getCameraByUsernameAndCid(username, cid);
    }

    /**
     * 接收PC摄像头端的socket请求，解析参数，存储摄像头的注册数据
     * 方法名：registerCamera
     * 创建人：夜幕团队
     * 时间：2018/1/22-22:01
     *
     * @param socket
     * @throws IOException
     */
    @Override
    public void registerCamera(Socket socket) throws IOException {
        String message = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        // 获取摄像头IP
        // String ip = socket.getInetAddress().getHostAddress();
        String line = reader.readLine();
        // System.out.println("=========================================="+line);

        String[] cameraParams = line.split("[$]");
        if (cameraParams.length != 8) {
            writer.write("请求错误，请重新检查请求参数是否正确");
            writer.flush();
            writer.close();
            return;
        }

        String begin_flag = cameraParams[0];
        String cid = cameraParams[1];
        String ip = cameraParams[2];
        String port = cameraParams[3];
        String cpassword = cameraParams[4];
        //用户信息
        String username = cameraParams[5];
        String password = cameraParams[6];
        String end_flag = cameraParams[7];

        if (begin_flag.equals(Const.BEGIN_FLAG)) {
            // 验证提交的所属用户是否为合法用户
            User user = userMapper.checkUser(username, MD5Util.MD5EncodeUtf8(password));
            if (user == null) {
                writer.write("用户信息校验错误，无法注册摄像头");
                writer.flush();
                writer.close();
                return;
            }

            Camera camera = new Camera();
            camera.setCid(cid);
            camera.setIp(ip);
            camera.setPort(Integer.parseInt(port));
            camera.setCpassword(cpassword);
            camera.setUser(username);

            // 验证该摄像头设备是否已存在
            int resultCount = cameraMapper.checkCid(camera.getCid());
            if (resultCount == 0) {
                // 如果不存在该设备，执行插入
                cameraMapper.insert(camera);
//                User userUpdate = new User();
//                userUpdate.setId(user.getId());
//                userUpdate.setCameraList(user.getCameraList() + camera.getCid() + ",");
//                userMapper.updateByPrimaryKeySelective(userUpdate);
                message = "用户" + username + " 新增设备成功";
            } else {
                // 如果该设备已存在，执行更新
                cameraMapper.updateByCid(camera);
//                User userUpdate = new User();
//                userUpdate.setId(user.getId());
//                if (!user.getCameraList().contains(camera.getCid())) {
//                    userUpdate.setCameraList(user.getCameraList() + camera.getCid() + ",");
//                } else {
//                    userUpdate.setCameraList(user.getCameraList());
//                }
//                userMapper.updateByPrimaryKeySelective(userUpdate);
                message = "用户" + username + " 更新设备成功";
            }

            if (end_flag.equals(Const.END_FLAG)) {
                writer.write(message);
                writer.flush();
                writer.close();
            } else {
                writer.write("连接失败，请重新检查参数是否正确");
                writer.flush();
                writer.close();
            }
        }
    }

}
