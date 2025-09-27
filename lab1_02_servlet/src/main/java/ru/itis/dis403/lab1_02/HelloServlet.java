package ru.itis.dis403.lab1_02;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@WebServlet(value = {"/hello"})
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
        logger.debug(servletRequest);
        String protocol = servletRequest.getProtocol();
        String remoteAddr = servletRequest.getRemoteAddr();
        int remotePort = servletRequest.getRemotePort();
        Map<String, String[]> parametres = servletRequest.getParameterMap();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>")
                .append("<div>protocol: ").append(protocol).append("</div>")
                .append("<div>remoteAddr: ").append(remoteAddr).append("</div>")
                .append("<div>remotePort: ").append(remotePort).append("</div>");

        parametres.entrySet().stream().forEach(e -> {
            sb.append("<div>param: ").append(e.getKey()).append("</div>");
                });
        sb.append("</body></html>");
        Writer writer = servletResponse.getWriter();
        writer.write(sb.toString());
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
