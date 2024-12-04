package com.fpoly.java4_youtube.controller;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.History;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.entity.Video;
import com.fpoly.java4_youtube.service.EmailService;
import com.fpoly.java4_youtube.service.HistoryService;
import com.fpoly.java4_youtube.service.VideoService;
import com.fpoly.java4_youtube.service.impl.EmailServiceImpl;
import com.fpoly.java4_youtube.service.impl.HistoryServiceImpl;
import com.fpoly.java4_youtube.service.impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/video"})
public class VideoServlet extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionParam = req.getParameter("action");
        String href = req.getParameter("id");
        HttpSession session = req.getSession();

        switch (actionParam) {
            case "watch":
                doGetWatch(session, href, req, resp);
                break;
            case "like":
                doGetLike(session, href, req, resp);
                break;
            case "share":
                doGetShare(req, resp);
                break;
        }
    }

    private void doGetWatch(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Video video = videoService.finByHref(href);
        req.setAttribute("video", video);
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        if (currentUser != null) {
            History history = historyService.create(currentUser, video);
            req.setAttribute("flagLikedBtn", history.getIsLiked());
        }
        req.getRequestDispatcher("/views/user/video-detail.jsp").forward(req, resp);
    }

    private void doGetLike(HttpSession session, String href, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);
        boolean result = historyService.updateLikeOrUnlike(currentUser, href);
        if (result) {
            resp.setStatus(204); // chuyển qua code javascript
        } else {
            resp.setStatus(400);
        }
    }

    private void doGetShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String href = req.getParameter("id");
        if (href == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing video id");
            return;
        }
        Video video = videoService.finByHref(href);
        if (video == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }
        req.setAttribute("video", video);
        req.getRequestDispatcher("/views/user/video-share.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("share".equals(action)) {
            handleShare(req, resp);
        }
    }

    private void handleShare(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String videoId = req.getParameter("id");
        if (email == null || videoId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing email or video id");
            return;
        }

        Video video = videoService.finByHref(videoId);
        if (video == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }

        // Sử dụng EmailService để gửi email
        EmailService emailService = new EmailServiceImpl();
        User recipient = new User();
        recipient.setEmail(email);
        recipient.setUsername("User");
        emailService.sendEmail(getServletContext(), recipient, "share-video");

        resp.sendRedirect(req.getContextPath() + "/video?action=watch&id=" + videoId);
    }
}
