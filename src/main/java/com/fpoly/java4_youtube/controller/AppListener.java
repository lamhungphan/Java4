package com.fpoly.java4_youtube.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {
    private static final String COUNTER_FILE = "/visitor_count.txt";
    private int visitorCount = 0;
    private File file;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            // Lấy file từ resources
            URL resourceUrl = getClass().getResource(COUNTER_FILE);
            if (resourceUrl == null) {
                throw new FileNotFoundException("File " + COUNTER_FILE + " not found in resources.");
            }

            // Copy file từ resources ra thư mục tạm nếu cần ghi
            file = new File("visitor_count.txt");
            if (!file.exists()) {
                Files.copy(resourceUrl.openStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Đọc số lượt truy cập từ file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                visitorCount = Integer.parseInt(reader.readLine());
            }
        } catch (IOException | NumberFormatException e) {
            visitorCount = 0; // Mặc định nếu không đọc được file
        }

        // Chia sẻ số đếm qua application scope
        context.setAttribute("visitors", visitorCount);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Tăng số lượt truy cập
        visitorCount++;
        // Cập nhật lại trong application scope
        ServletContext context = se.getSession().getServletContext();
        context.setAttribute("visitors", visitorCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Lưu số đếm trở lại file khi ứng dụng ngừng hoạt động
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(visitorCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
