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

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(UserCheckServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String resource = "/index.ftlh";

        if (session == null || session.getAttribute("user") == null) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // select id, username, password from users where username = ? ;

            if (username.equals("admin") && password.equals("admin")) {
                resource = "/index.ftlh";
            } else {
                request.setAttribute("errormessage", "Неверное имя пользователя или пароль!");
                resource = "/login.ftlh";
            }
        }

        request.getRequestDispatcher(resource)
                .forward(request, response);
    }

}
