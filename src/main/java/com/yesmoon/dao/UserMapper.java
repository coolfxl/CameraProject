package com.yesmoon.dao;

import com.yesmoon.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User checkUser(@Param("username") String username, @Param("password") String password);

    User checkLogin(@Param("username") String username, @Param("password") String password);

    int updateTokenByUsername(@Param("username") String username, @Param("token") String token);

    int checkLoginState(@Param("username") String username, @Param("token") String token);
}