package com.fpoly.java4_youtube.service;

import com.fpoly.java4_youtube.dto.VideoLikedInfo;

import java.util.List;

public interface StatsService {
    List<VideoLikedInfo> findVideoLikedInfo();
}
