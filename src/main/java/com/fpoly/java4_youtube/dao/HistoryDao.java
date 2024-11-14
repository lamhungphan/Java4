package com.fpoly.java4_youtube.dao;

import com.fpoly.java4_youtube.entity.History;

import java.util.List;


public interface HistoryDao {
    // Cac video username da xem
    List<History> findByUser(String username);

    // Cac video username da xem va like
    List<History> findByUserAndIsLiked(String username);

    History findByUserAndVideoId(Integer userId, Integer videoId);

    History create(History entity);

    History update(History entity);
}
