package com.fpoly.java4_youtube.service;


import com.fpoly.java4_youtube.entity.Video;

import java.util.List;

public interface VideoService {
	Video findById(Integer id);
	Video finByHref(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber ,int pageSize);
	Video create (Video entity);
	Video update (Video entity);
	Video delete (String href);
}
