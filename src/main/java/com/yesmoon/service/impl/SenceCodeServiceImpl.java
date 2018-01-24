package com.yesmoon.service.impl;

import com.yesmoon.dao.SenceCodeMapper;
import com.yesmoon.pojo.SenceCode;
import com.yesmoon.service.ISenceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SenceCodeServiceImpl
 * 创建人:夜幕团队
 * 时间：2018年01月23日-下午19:27
 *
 * @version 1.0.0
 */
@Service
public class SenceCodeServiceImpl implements ISenceCodeService {

    @Autowired
    private SenceCodeMapper senceCodeMapper;

    @Override
    public SenceCode getSenceCode() {
        return senceCodeMapper.getLastSenceCode();
    }
}
