package ru.itis.dis403.lab01_04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab01_04.service.AirplaneService;

import java.io.IOException;

@WebServlet("/airplanes")
public class AirplanesServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AirplanesServlet.class);

    private AirplaneService airplaneService = new AirplaneService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        airplaneService.fillAttributes(request);
        request.getRequestDispatcher("/airplanes.ftlh")
                .forward(request, response);
    }

}
