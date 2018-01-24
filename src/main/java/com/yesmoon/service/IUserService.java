package com.yesmoon.service;

import com.yesmoon.pojo.User;
import com.yesmoon.util.YmResult;

public interface IUserService {

    YmResult login(String username, String password);

    int logout(String username);

    YmResult register(User user);

    boolean checkValidate(String username);

    int checkLoginState(String username, String token);

}
