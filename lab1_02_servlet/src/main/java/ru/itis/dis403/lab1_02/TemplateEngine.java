package ru.itis.dis403.lab1_02;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("*.html")
public class TemplateEngine extends HttpServlet {

    final static Logger logger = LogManager.getLogger(TemplateEngine.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.debug(request.getServletPath());
    }
}
