package com.fpoly.java4_youtube.service;

import com.fpoly.java4_youtube.entity.History;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.entity.Video;

import java.util.List;

public interface HistoryService {
    List<History> findByUser(String username);

    List<History> findByUserAndIsLiked(String username);

    History findByUserIdAndVideoId(Integer userId, Integer videoId);

    History create(User user, Video video);

    boolean updateLikeOrUnlike(User user, String videoHref);
}
