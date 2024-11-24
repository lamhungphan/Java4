package com.fpoly.java4_youtube.controller;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.EmailService;
import com.fpoly.java4_youtube.service.UserService;
import com.fpoly.java4_youtube.service.impl.EmailServiceImpl;
import com.fpoly.java4_youtube.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/login", "/logout", "/register", "/changePass", "/forgotPass", "/updateAccount"})
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private EmailService emailService = new EmailServiceImpl();

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
            case "/changePass":
                doGetChangePass(req, resp);
                break;
            case "/forgotPass":
                doGetForgotPass(req, resp);
                break;
            case "/updateAccount":
                doGetUpdateAccount(session, req, resp);
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
            case "/changePass":
                doPostChangePass(session, req, resp);
                break;
            case "/forgotPass":
                doPostForgotPass(req, resp);
                break;
            case "/updateAccount":
                doPostUpdateAccount(session, req, resp);
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

    private void doGetChangePass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/change-password.jsp").forward(req, resp);
    }

    private void doGetForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/forgot-password.jsp").forward(req, resp);
    }

    private void doGetUpdateAccount(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/change-info.jsp").forward(req, resp);
    }

    private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute(SessionAttribute.CURRENT_USER, user);
            session.setAttribute("role", user.getIsAdmin()); // Lưu vai trò của user
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
            emailService.sendEmail(getServletContext(), user, "welcome");
            session.setAttribute(SessionAttribute.CURRENT_USER, user);
            session.setAttribute("role", user.getIsAdmin());
            resp.sendRedirect("login");
        } else {
            req.setAttribute("error", "Registration failed. Try again.");
            resp.sendRedirect("register");
        }
    }

    private void doPostChangePass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("/application/json");
        String currPass = req.getParameter("currPass");
        String newPass = req.getParameter("newPass");
        String confirmPass = req.getParameter("confirmPass");

        User currUser = (User) session.getAttribute(SessionAttribute.CURRENT_USER);

        PrintWriter out = resp.getWriter();
        if (currUser == null) {
            resp.setStatus(401); // Unauthorized
            out.write("{\"message\":\"User is not logged in.\"}");
            return;
        }

        if (!currUser.getPassword().equals(currPass)) {
            resp.setStatus(400);
            out.write("{\"message\":\"Current password is incorrect\"}");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            resp.setStatus(400);
            out.write("{\"message\":\"New password and confirmation password do not match\"}");
            return;
        }

        currUser.setPassword(newPass);
        User updatedUser = userService.update(currUser);

        if (updatedUser != null) {
            session.setAttribute(SessionAttribute.CURRENT_USER, updatedUser);
            resp.setStatus(200); // OK
            out.write("{\"message\":\"Password has been changed successfully\"}");
        } else {
            resp.setStatus(500); // Internal Server Error
            out.write("{\"message\":\"An error occurred while updating the password\"}");
        }
    }

    private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("/application/json");
        String email = req.getParameter("email");

        User userWithNewPass = userService.resetPassword(email);

        if (userWithNewPass != null) {
            emailService.sendEmail(getServletContext(), userWithNewPass, "forgot");
            resp.setStatus(204);
        } else {
            resp.setStatus(400);
        }
    }

    private void doPostUpdateAccount(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
    }
}
