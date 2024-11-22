package com.fpoly.java4_youtube.controller;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.UserService;
import com.fpoly.java4_youtube.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet( {"/login", "/logout", "/register"})
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        switch (path) {
            case "/login":
                doGetLogin(req, resp);
                break;
            case "/register":
                doGetRegister(req, resp);
                break;
            case "/logout":
                doGetLogout(session, req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String path = req.getServletPath();
        switch (path) {
            case "/login":
                doPostLogin(session, req, resp);
                break;
            case "/register":
                doPostRegister(session, req, resp);
                break;
        }
    }

    private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
    }

    private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
    }

    private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session.removeAttribute(SessionAttribute.CURRENT_USER);
        resp.sendRedirect("index");
    }

    private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute(SessionAttribute.CURRENT_USER, user);
            session.setAttribute("role", user.getIsAdmin()); // Lưu thêm vai trò của user
            resp.sendRedirect("index");
        } else {
            req.setAttribute("error", "Invalid username or password");
            resp.sendRedirect("login");
        }
    }

    private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        User user = userService.create(username, password, email);

        if (user != null) {
            session.setAttribute(SessionAttribute.CURRENT_USER, user);
            session.setAttribute("role", user.getIsAdmin());
            resp.sendRedirect("login");
        } else {
            req.setAttribute("error", "Registration failed. Try again.");
            resp.sendRedirect("register");
        }
    }
}
