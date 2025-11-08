package ru.itis.dis403.lab1_07.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(IndexServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Сюда может попасть только аутентифицированный пользователь
        // значит в его сессии должен быть атрибут "user" (который мы положили при аутентификации)
        request.setAttribute("user", request.getSession().getAttribute("user"));

        request.getRequestDispatcher("/index.ftlh")
                .forward(request, response);
    }
}
