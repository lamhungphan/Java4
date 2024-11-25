package com.fpoly.java4_youtube.filter;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/account/change-password", "/account/edit-profile", "/video/like/*", "/video/share/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy session hiện tại
        HttpSession session = httpRequest.getSession(false);

        // Lấy User từ session
        User currentUser = (session != null) ? (User) session.getAttribute(SessionAttribute.CURRENT_USER) : null;

        // Kiểm tra đăng nhập
        boolean isLoggedIn = (currentUser != null);

        // Nếu chưa đăng nhập, chuyển hướng login
        if (!isLoggedIn) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        // URL hiện tại
        String requestURI = httpRequest.getRequestURI();

        // Kiểm tra quyền admin nếu truy cập /admin/*
        if (requestURI.startsWith(httpRequest.getContextPath() + "/admin")) {
            boolean isAdmin = currentUser.getIsAdmin();

            // Nếu không phải admin, trả lỗi 403
            if (!isAdmin) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
                return;
            }
        }

        // Cho phép tiếp tục nếu hợp lệ
        chain.doFilter(request, response);
    }
}
