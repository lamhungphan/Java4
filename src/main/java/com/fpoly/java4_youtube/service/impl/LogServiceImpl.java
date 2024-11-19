package com.fpoly.java4_youtube.service.impl;

import com.fpoly.java4_youtube.dao.LogDao;
import com.fpoly.java4_youtube.dao.impl.LogDaoImpl;
import com.fpoly.java4_youtube.entity.Log;
import com.fpoly.java4_youtube.service.LogService;

public class LogServiceImpl implements LogService {
    LogDao dao = new LogDaoImpl();

    @Override
    public Log save(Log entity) {
        return dao.create(entity);
    }
}
