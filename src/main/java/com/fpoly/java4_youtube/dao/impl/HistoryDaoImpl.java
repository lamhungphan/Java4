package com.fpoly.java4_youtube.dao.impl;

import com.fpoly.java4_youtube.dao.AbstractDao;
import com.fpoly.java4_youtube.dao.HistoryDao;
import com.fpoly.java4_youtube.entity.History;

import java.util.List;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {
    @Override
    public List<History> findByUser(String username) {
        String sql = "SELECT o FROM History o WHERE o.user.username = ?1 AND o.video.isActive = true"
                + " ORDER BY o.viewedDate DESC";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        String sql = "SELECT o FROM History o WHERE o.user.username = ?1 AND o.isLiked = true"
                + " AND o.video.isActive = true"
                + " ORDER BY o.viewedDate DESC";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        String sql = "SELECT o FROM History o WHERE o.user.id = ?1 AND o.video.id = ?2"
                + " AND o.video.isActive = true";
        return super.findOne(History.class, sql, userId, videoId);
    }
}
