package com.fpoly.java4_youtube.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpoly.java4_youtube.dto.UserDTO;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/favorite"})
public class FavoriteController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoHref = req.getParameter("href");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (videoHref == null || videoHref.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing or invalid href parameter\"}");
            return;
        }

        List<User> users = userService.findUsersLikedVideoByVideoHref(videoHref);
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(userDTOs));

    }
}
