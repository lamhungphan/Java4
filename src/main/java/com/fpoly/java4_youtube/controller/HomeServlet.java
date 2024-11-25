package com.fpoly.java4_youtube.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.History;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.HistoryService;
import com.fpoly.java4_youtube.service.VideoService;
import com.fpoly.java4_youtube.service.impl.HistoryServiceImpl;
import com.fpoly.java4_youtube.service.impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/index", "/favorite", "/history"})
public class HomeServlet extends HttpServlet {
    private static final int VIDEO_MAX_PAGE_SIZE = 2;
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        switch (path) {
            case "/index":
                doGetIndex(req, resp);
                break;
            case "/favorite":
                doGetFavorite(session, req, resp);
                break;
            case "/history":
                doGetHistory(session, req, resp);
                break;
        }
    }

    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> countVideo = videoService.findAll();
        int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
        req.setAttribute("maxPage", maxPage);
        String pageNumber = req.getParameter("page");

        List<Video> videos;
        if (pageNumber == null || maxPage < Integer.valueOf(pageNumber)) {
            videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
            req.setAttribute("currentPage", 1);
        } else {
            videos = videoService.findAll(Integer.parseInt(pageNumber), VIDEO_MAX_PAGE_SIZE);
            req.setAttribute("currentPage", Integer.valueOf(pageNumber));
        }

        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }

    private void doGetFavorite(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
        List<Video> videos = new ArrayList<>();
        histories.forEach(item -> videos.add(item.getVideo()));
        /* đưa video trong history vào mảng videos rỗng
        for (int i = 0; i < histories.size(); i++) {
            videos.add(histories).get(i). getVideo());
         }
         */
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/user/favorite.jsp").forward(req, resp);
    }

    private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        List<History> histories = historyService.findByUser(user.getUsername());
        List<Video> videos = new ArrayList<>();
        histories.forEach(item -> videos.add(item.getVideo()));

        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/views/user/history.jsp").forward(req, resp);
    }
}
