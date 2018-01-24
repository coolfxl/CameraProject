package com.yesmoon.dao;

import com.yesmoon.pojo.Camera;
import org.apache.ibatis.annotations.Param;

public interface CameraMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Camera record);

    int insertSelective(Camera record);

    Camera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Camera record);

    int updateByPrimaryKey(Camera record);

    int checkCid(String cid);

    int updateByCid(Camera camera);

    Camera getCameraByUsernameAndCid(@Param("username") String username, @Param("cid") String cid);
}