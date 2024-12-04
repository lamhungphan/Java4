package com.fpoly.java4_youtube.controller.admin;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.UserService;
import com.fpoly.java4_youtube.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/admin/user")
public class UserController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "view":
                doGetOverview(req, resp);
                break;
            case "edit":
                doGetEdit(req, resp);
                break;
            case "add":
                req.setAttribute("isEdit", false);
                req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                doPostAdd(req, resp);
                break;
            case "edit":
                doPostEdit(req, resp);
                break;
            case "delete":
                doPostDelete(req, resp);
                break;
        }
    }

    private void doGetOverview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userService.findAll();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/views/admin/user-overview.jsp").forward(req, resp);
    }

    private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findById(id);
        req.setAttribute("user", user);
        req.setAttribute("isEdit", true);
        req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
    }

    private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        userService.create(username, password, email);
        resp.sendRedirect("/admin/user?action=view");
    }

    private void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isAdmin = Boolean.parseBoolean(req.getParameter("isAdmin"));

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userService.update(user);
        resp.sendRedirect("/admin/user?action=view");
    }

    private void doPostDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        userService.delete(username);
        resp.sendRedirect("/admin/user?action=view");
    }
}
