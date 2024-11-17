package com.fpoly.java4_youtube.controller;

import java.io.IOException;
import java.util.List;

import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.VideoService;
import com.fpoly.java4_youtube.service.impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/index")
public class HomeServlet extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videos = videoService.findAll();
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }

}
