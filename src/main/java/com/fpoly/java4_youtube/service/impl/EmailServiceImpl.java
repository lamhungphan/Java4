package com.fpoly.java4_youtube.service.impl;

import com.fpoly.java4_youtube.entity.User;
import com.fpoly.java4_youtube.service.EmailService;
import com.fpoly.java4_youtube.util.SendEmailUtil;
import jakarta.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {
    private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertainment";
    private static final String EMAIL_FORGOT_PASSWORD = "Online Entertainment - New password";

    @Override
    public void sendEmail(ServletContext context, User recipient, String type) {
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        try {
            String content = null;
            String subject = null;
            switch (type) {
                case "welcome":
                    subject = EMAIL_WELCOME_SUBJECT;
                    content = "Dear " + recipient.getUsername() + ", we hope you enjoy our website ^^";
                    break;
                case "forgot":
                    subject = EMAIL_FORGOT_PASSWORD;
                    content = "Hi " + recipient.getUsername() + ", here is your new password " +
                            "\n" + recipient.getPassword();
                    break;
                default:
                    subject = "Online Entertainment";
                    content = "this is just a test email";
                    break;
            }
            SendEmailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
