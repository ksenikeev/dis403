package ru.itis.dis403.lab1_05.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        request.getRequestDispatcher("/login.ftlh")
                .forward(request, response);
    }
}
