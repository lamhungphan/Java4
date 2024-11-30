package com.fpoly.java4_youtube.controller.admin;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.VideoService;
import com.fpoly.java4_youtube.service.impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/video"}, name = "VideoControllerOfAdmin")
public class VideoController extends HttpServlet {
    VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
            String action = req.getParameter("action");
            switch (action) {
                case "view":
                    doGetOverview(req, resp);
                    break;
                case "delete":
                    doGetDelete(req, resp);
                    break;
                case "add":
                    req.setAttribute("isEdit",false);
                    doGetAdd(req, resp);
                    break;
                case "edit":
                    req.setAttribute("isEdit",true);
                    doGetEdit(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
            String action = req.getParameter("action");
            switch (action) {
                case "add":
                    doPostAdd(req, resp);
                    break;
                case "edit":
                    doPostEdit(req, resp);
                    break;
            }
        } else {
            resp.sendRedirect("index");
        }
    }

    protected void doGetOverview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Video> videoList = videoService.findAll();
        req.setAttribute("videoList", videoList);
        req.getRequestDispatcher("/views/admin/video-overview.jsp").forward(req, resp);
    }

    protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String href = req.getParameter("href");
        Video videoDelete = videoService.delete(href);

        if (videoDelete != null) {
            resp.setStatus(204);
        } else {
            resp.setStatus(400);
        }
    }

    protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
    }

    protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String href = req.getParameter("href");
        Video video = videoService.finByHref(href);
        req.setAttribute("video", video);
        req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
    }

    protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String title = req.getParameter("title");
        String href = req.getParameter("href");
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");

        Video video = new Video();
        video.setTitle(title);
        video.setHref(href);
        video.setDescription(description);
        video.setPoster(poster);

        Video videoReturn = videoService.create(video);

        if (videoReturn != null) {
            resp.setStatus(204);
        } else {
            resp.setStatus(400);
        }
    }

    protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String title = req.getParameter("title");
        String href = req.getParameter("newHref");
        String description = req.getParameter("description");
        String poster = req.getParameter("poster");
        String hrefOrigin = req.getParameter("hrefOrigin");

        Video video = videoService.finByHref(hrefOrigin);
        video.setTitle(title);
        video.setHref(href);
        video.setDescription(description);
        video.setPoster(poster);

        Video videoReturn = videoService.update(video);

        if (videoReturn != null) {
            resp.setStatus(204);
        } else {
            resp.setStatus(400);
        }
    }
}
