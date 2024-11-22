package com.fpoly.java4_youtube.controller.admin;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.dto.VideoLikedInfo;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.StatsService;
import com.fpoly.java4_youtube.service.impl.StatsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin","/admin/dashboard","/admin/favorite"}, name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet {
    private StatsService statsService = new StatsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE){
           List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
           req.setAttribute("videos", videos);

            if (path.contains("dashboard")) {
                req.setAttribute("view", "/views/admin/dashboard.jsp");
            } else if (path.contains("favorite")) {
                req.setAttribute("view", "/views/admin/favorite.jsp");
            }

            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("index");
        }
    }
}
