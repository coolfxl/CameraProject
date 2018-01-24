package com.yesmoon.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String USERNAME = "username";

    public static final String BEGIN_FLAG = "begin";

    public static final String END_FLAG = "end";

    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户

        int ROLE_ADMIN = 1; // 管理员
    }

}
