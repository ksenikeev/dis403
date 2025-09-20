package ru.itis.dis403.lab1_02;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(value = "/hello")
public class HelloServlet implements Servlet {

    final static Logger logger = LogManager.getLogger(HelloServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.debug(servletConfig.getServletName());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.debug(servletRequest.getClass().getName());
        String html = "";
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
