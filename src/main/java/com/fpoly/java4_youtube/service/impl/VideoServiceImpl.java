package com.fpoly.java4_youtube.service.impl;


import com.fpoly.java4_youtube.dao.VideoDao;
import com.fpoly.java4_youtube.dao.impl.VideoDaoImpl;
import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {

    private VideoDao dao;

    public VideoServiceImpl(){
        dao = new VideoDaoImpl();
    }

    @Override
    public Video findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Video finByHref(String href) {
        return dao.findByHref(href);
    }

    @Override
    public List<Video> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSize) {
        return dao.findAll(pageNumber, pageSize);
    }

    @Override
    public Video create(Video entity) {
        // Co the xu li bang annotations trong Entity
        entity.setIsActive(Boolean.TRUE);
        entity.setViews(0);
        entity.setShares(0);
        return dao.create(entity);
    }

    @Override
    public Video update(Video entity) {
        return dao.update(entity);
    }

    @Override
    public Video delete(String href) { // thuc te co the se khong duoc dung ID => dung unique field khac
        Video entity = finByHref(href);
        entity.setIsActive(Boolean.FALSE);
        return dao.update(entity);
    }
}
