package com.fpoly.java4_youtube.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.dto.VideoLikedInfo;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.StatsService;
import com.fpoly.java4_youtube.service.UserService;
import com.fpoly.java4_youtube.service.impl.StatsServiceImpl;
import com.fpoly.java4_youtube.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin", "/admin/dashboard", "/admin/favorite"}, name = "HomeControllerOfAdmin")
public class HomeController extends HttpServlet {
    private StatsService statsService = new StatsServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        if (currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {

            switch (path) {
                case "/admin":
                    doGetIndex(req, resp);
                    break;
                case "/admin/dashboard":
                    doGetDashboard(req, resp);
                    break;
                case "/admin/favorite":
                    doGetFavorite(req, resp);
                    break;
                default:
                    req.setAttribute("view", "/views/admin/dashboard.jsp");
                    break;
            }
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("index");
        }
    }

    private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }

    private void doGetFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Lấy tham số href
        String videoHref = req.getParameter("href");
        if (videoHref == null || videoHref.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Lỗi 400: Bad Request
            out.print("{\"error\": \"Missing or invalid href parameter\"}");
            out.flush();
            return;
        }

        List<User> users = userService.findUsersLikedVideoByVideoHref(videoHref);

        if (users.isEmpty()) {
            resp.setStatus(400);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String dataResponse = mapper.writeValueAsString(users); // Convert danh sách thành JSON
            resp.setStatus(200);
            out.print(dataResponse);
            out.flush();
        }
        req.setAttribute("view", "/views/admin/favorite.jsp");
    }

    private void doGetDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
        req.setAttribute("videos", videos);
        req.setAttribute("view", "/views/admin/dashboard.jsp");
    }
}
