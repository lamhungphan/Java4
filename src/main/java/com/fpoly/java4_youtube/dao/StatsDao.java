package com.fpoly.java4_youtube.dao;

import com.fpoly.java4_youtube.dto.VideoLikedInfo;

import java.util.List;

public interface StatsDao {
    List<VideoLikedInfo> findVideoLikedInfo();

}
