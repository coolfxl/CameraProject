package com.yesmoon.service.impl;

import com.yesmoon.dao.SenceCodeMapper;
import com.yesmoon.pojo.SenceCode;
import com.yesmoon.service.ISenceCodeSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * SenceCodeSocketServiceImpl
 * 创建人:夜幕团队
 * 时间：2018年01月23日-下午19:21
 *
 * @version 1.0.0
 */
@Service
public class SenceCodeSocketServiceImpl implements ISenceCodeSocketService {

    @Autowired
    private SenceCodeMapper senceCodeMapper;

    @Override
    public int insertSenceCode(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = reader.readLine();
        SenceCode senceCodePOJO = new SenceCode();
        senceCodePOJO.setSenceCodeJson(line);
        return senceCodeMapper.insertSelective(senceCodePOJO);
    }
}
