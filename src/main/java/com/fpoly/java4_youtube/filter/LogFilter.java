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

import java.io.IOException;

@WebFilter({"/admin/*"})
public class LogFilter implements Filter {
    private LogService logService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (logService == null) {
            logService = new LogServiceImpl();
        }

        User loggedUser = getLoggedUser(req);
        Log log = configLog(loggedUser.getEmail(), req);
        logService.save(log);
        chain.doFilter(req, resp);
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
