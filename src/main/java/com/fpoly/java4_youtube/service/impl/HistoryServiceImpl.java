package com.fpoly.java4_youtube.service.impl;

import com.fpoly.java4_youtube.dao.HistoryDao;
import com.fpoly.java4_youtube.dao.impl.HistoryDaoImpl;
import com.fpoly.java4_youtube.entity.History;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.HistoryService;
import com.fpoly.java4_youtube.service.VideoService;

import java.sql.Timestamp;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDao dao;
    private VideoService videoService = new VideoServiceImpl();

    public HistoryServiceImpl() {
        dao = new HistoryDaoImpl();
    }

    @Override
    public List<History> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        return dao.findByUserAndIsLiked(username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        return dao.findByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public History create(User user, Video video) {
        History history = new History();
        history.setUser(user);
        history.setVideo(video);
        history.setIsLiked(Boolean.FALSE);
        return dao.create(history);
    }

    @Override
    public boolean updateLikeOrUnlike(User user, String videoHref) {
        Video video = videoService.finByHref(videoHref);
        History existedHistory = findByUserIdAndVideoId(user.getId(), video.getId());

        if (existedHistory.getIsLiked() == Boolean.FALSE) {
            existedHistory.setIsLiked(Boolean.TRUE);
            existedHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
        } else {
            existedHistory.setIsLiked(Boolean.FALSE);
            existedHistory.setIsLiked(null);
        }
        History updateHistory = dao.update(existedHistory);
        return updateHistory != null ? true : false;
    }
}
