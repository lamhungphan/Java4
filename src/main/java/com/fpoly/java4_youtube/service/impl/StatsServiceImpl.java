package com.fpoly.java4_youtube.service.impl;

import com.fpoly.java4_youtube.dao.StatsDao;
import com.fpoly.java4_youtube.dao.impl.StatsDaoImpl;
import com.fpoly.java4_youtube.dto.VideoLikedInfo;
import com.fpoly.java4_youtube.service.StatsService;

import java.util.List;

public class StatsServiceImpl implements StatsService {
    private StatsDao statsDao;

    public StatsServiceImpl() {
        statsDao = new StatsDaoImpl();
    }

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        return statsDao.findVideoLikedInfo();
    }
}
