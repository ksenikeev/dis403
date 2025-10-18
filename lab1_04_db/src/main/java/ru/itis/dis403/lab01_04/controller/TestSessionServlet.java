package ru.itis.dis403.lab01_04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab01_04.service.AirplaneService;

import java.io.IOException;

@WebServlet("/session")
public class TestSessionServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(TestSessionServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean flag = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("JSESSIONID")) {
                    request.setAttribute("sessionId", c.getValue());
                    flag = true;
                    break;
                }
            }
        }



        if (!flag) {
            request.setAttribute("sessionId", "JSESSIONID не нашли");
            Cookie cookie = new Cookie("JSESSIONID", "123");
            response.addCookie(cookie);
        }

        request.getRequestDispatcher("/session.ftlh")
                .forward(request, response);
    }

}
