package ru.itis.dis403.lab1_07.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab1_07.model.City;
import ru.itis.dis403.lab1_07.repository.CityRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/","/index"})
public class IndexServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(IndexServlet.class);

    private CityRepository cityRepository;

    public void init() {
        ServletContext context = getServletContext();
        cityRepository =
                (CityRepository) context.getAttribute("cityRepository");
        logger.debug("cityRepository = " + cityRepository);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<City> cities = cityRepository.getAll();
            request.setAttribute("cities", cities);
        } catch (Exception e) {
            logger.atError().withThrowable(e).log();
        }

        request.getRequestDispatcher("/index.ftlh")
                .forward(request, response);
    }
}
