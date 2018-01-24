package com.yesmoon.dao;

import com.yesmoon.pojo.SenceCode;

public interface SenceCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SenceCode record);

    int insertSelective(SenceCode record);

    SenceCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SenceCode record);

    int updateByPrimaryKey(SenceCode record);

    SenceCode getLastSenceCode();
}