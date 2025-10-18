package ru.itis.dis403.lab01_04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/truesession")
public class SessionServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(SessionServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // ищется кука
        // ищется ассоциированная с ней сессия (объект HttpSession)
        // если не находится - создается новый (если не указан флаг false)
        HttpSession session = request.getSession(false);

        if (session == null) {
            session = request.getSession(true);
            session.setAttribute("username", request.getParameter("user"));
            request.setAttribute("username", "инкогнито");
        } else {
            request.setAttribute("username", session.getAttribute("username"));
        }


        request.setAttribute("sessionId", session.getId());

        request.getRequestDispatcher("/session.ftlh")
                .forward(request, response);
    }

}
