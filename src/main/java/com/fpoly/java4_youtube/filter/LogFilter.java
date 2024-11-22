package com.fpoly.java4_youtube.filter;

import com.fpoly.java4_youtube.constant.SessionAttribute;
import com.fpoly.java4_youtube.entity.Log;
import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.LogService;
import com.fpoly.java4_youtube.service.impl.LogServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

@WebFilter("/*")
public class LogFilter implements Filter {
    private LogService logService = new LogServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        User currentUser = (User)  session.getAttribute(SessionAttribute.CURRENT_USER);
        String username = (currentUser != null) ? currentUser.getUsername() : null;

        if (username != null) {
            Log log = new Log();
            log.setUrl(uri);
            log.setTime(new Timestamp(System.currentTimeMillis()));
            log.setUsername(username);
            logService.save(log);
        }
        chain.doFilter(request, response);
    }

    public Log configLog(String mail, HttpServletRequest request) {
        Log log = new Log();
        String url = request.getRequestURI();
        log.setUrl(url);
        log.setUsername(mail);
        return log;
    }

    public User getLoggedUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SessionAttribute.CURRENT_USER);
    }
}
