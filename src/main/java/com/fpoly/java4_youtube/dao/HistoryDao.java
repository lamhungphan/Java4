package com.fpoly.java4_youtube.dao;

import com.fpoly.java4_youtube.entity.History;

import java.util.List;


public interface HistoryDao {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	List<History> findByUserAndVideoId(Integer userId, Integer videoId);
	History create(History entity);
	History update(History entity);
}
