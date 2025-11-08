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
import ru.itis.dis403.lab1_07.model.Street;
import ru.itis.dis403.lab1_07.repository.CityRepository;
import ru.itis.dis403.lab1_07.repository.StreetRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/streets"})
public class StreetServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(StreetServlet.class);

    private CityRepository cityRepository;
    private StreetRepository streetRepository;

    public void init() {
        ServletContext context = getServletContext();
        streetRepository =
                (StreetRepository) context.getAttribute("streetRepository");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("city"));
            List<Street> streets = streetRepository.findByCityId(id);

            StringBuilder json = new StringBuilder("{\"streets\":[");
            streets.forEach(s -> json.append("\"" + s.getName() + "\","));

            json.deleteCharAt(json.length() - 1).append("]}");

            response.getWriter().write(json.toString());

        } catch (Exception e) {
            logger.atError().withThrowable(e).log();
        }
    }
}
