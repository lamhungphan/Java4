package com.fpoly.java4_youtube.dao.impl;

import com.fpoly.java4_youtube.dao.AbstractDao;
import com.fpoly.java4_youtube.dao.LogDao;
import com.fpoly.java4_youtube.entity.Log;

public class LogDaoImpl extends AbstractDao<Log> implements LogDao {
    @Override
    public Log create(Log entity) {
        return super.create(entity);
    }
}
