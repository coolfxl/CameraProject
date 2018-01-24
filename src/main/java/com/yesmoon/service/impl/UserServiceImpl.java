package com.yesmoon.service.impl;

import com.yesmoon.common.Const;
import com.yesmoon.common.ResponseCode;
import com.yesmoon.dao.SenceCodeMapper;
import com.yesmoon.dao.UserMapper;
import com.yesmoon.pojo.SenceCode;
import com.yesmoon.pojo.User;
import com.yesmoon.service.IUserService;
import com.yesmoon.util.MD5Util;
import com.yesmoon.util.YmResult;
import com.yesmoon.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SenceCodeMapper senceCodeMapper;

    @Override
    public YmResult login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return YmResult.build(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLogin(username, md5Password);
        if (user == null) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "用户密码错误");
        }

        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setCameraList(user.getCameraList());
        userVo.setRole(user.getRole());

        // 生成设置token
        String token = UUID.randomUUID().toString();
        userMapper.updateTokenByUsername(user.getUsername(), token);
        userVo.setToken(token);

        // 查询设置手机侦码
        SenceCode senceCode = senceCodeMapper.getLastSenceCode();
        userVo.setSenceCode(senceCode);
        return YmResult.ok(userVo);
    }

    @Override
    public int logout(String username) {
        return userMapper.updateTokenByUsername(username, null);
    }

    @Override
    public YmResult register(User user) {
        // 校验用户名
        if (checkValidate(user.getUsername())) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "用户名已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return YmResult.build(ResponseCode.ERROR.getCode(), "注册失败");
        }
        return YmResult.ok("注册成功");
    }

    @Override
    public boolean checkValidate(String username) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int checkLoginState(String username, String token) {
        return userMapper.checkLoginState(username, token);
    }

}
