package com.fpoly.java4_youtube.dao.impl;

import com.fpoly.java4_youtube.dao.AbstractDao;
import com.fpoly.java4_youtube.dao.HistoryDao;
import com.fpoly.java4_youtube.entity.History;

import java.util.List;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {
    @Override
    public List<History> findByUser(String username) {
        String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.video.isActive = 1"
                + " ORDER BY o.viewedDate DESC";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1"
                + " AND o.video.isActive = 1"
                + " ORDER BY o.viewedDate DESC";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public History findByUserAndVideoId(Integer userId, Integer videoId) {
        String sql = "SELECT o FROM History o WHERE o.user.userId = ?0 AND o.video.videoId = ?1"
                + " AND o.video.isActive = 1";
        return super.findOne(History.class, sql, userId, videoId);
    }

}
