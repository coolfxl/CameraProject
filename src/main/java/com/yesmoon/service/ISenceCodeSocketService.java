package com.yesmoon.service;

import java.io.IOException;
import java.net.Socket;

/**
 * ISenceCodeSocketService
 * 创建人:夜幕团队
 * 时间：2018年01月24日-下午10:56
 *
 * @version 1.0.0
 */
public interface ISenceCodeSocketService {

    int insertSenceCode(Socket socket) throws IOException;
}
