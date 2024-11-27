package com.fpoly.java4_youtube.dao.impl;

import com.fpoly.java4_youtube.dao.AbstractDao;
import com.fpoly.java4_youtube.dao.StatsDao;
import com.fpoly.java4_youtube.dto.VideoLikedInfo;
import com.fpoly.java4_youtube.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {
    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        String sql = "SELECT v.id, v.title, v.href, SUM(CAST(h.isLiked AS UNSIGNED)) as totalLike" +
                " FROM video v LEFT JOIN history h ON v.id = h.videoId" +
                " WHERE v.isActive = 1" +
                " GROUP BY v.id, v.title, v.href" +
                " ORDER BY totalLike DESC";
        List<Object[]> objects = super.findManyByNativeQuery(sql);
        List<VideoLikedInfo> result = new ArrayList<>();
        objects.forEach(object -> {
            VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);
            result.add(videoLikedInfo);
        });
        return result;
    }

    private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
        VideoLikedInfo info = new VideoLikedInfo();
        info.setVideoId(((Number) object[0]).intValue());
        info.setTitle((String) object[1]);
        info.setHref((String) object[2]);
        info.setTotalLike(object[3] == null ? 0 : ((Number) object[3]).intValue());
        return info;
    }
}
