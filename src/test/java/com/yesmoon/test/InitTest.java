package com.yesmoon.test;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * InitTest
 * 创建人:夜幕团队
 * 时间：2018年01月23日-下午11:13
 *
 * @version 1.0.0
 */
public class InitTest {

    @Test
    public void testSocket(){
        try {
            // 创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.0.1", 10010);
            // 建立连接后，获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();
            // 输出流包装为打印流
            PrintWriter pw = new PrintWriter(os);
            // 向服务器端发送信息
            pw.write("begin$seq-1$127.0.0.1$10000$000$admin$admin$end");// 写入内存缓冲区
            pw.flush();// 刷新缓存，向服务器端输出信息
            socket.shutdownOutput();

            // 获取输入流，接收服务器端响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = br.readLine();
            System.out.println("服务器端返回的信息为：" + data);

            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
