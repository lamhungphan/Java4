package com.fpoly.java4_youtube.service;

import com.fpoly.java4_youtube.entity.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;

public interface EmailService {
    void sendEmail(ServletContext context, User recipient, String type);
}
