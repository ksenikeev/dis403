package ru.itis.dis403.lab1_02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest,
                      HttpServletResponse servletResponse) throws ServletException, IOException {
        String protocol = servletRequest.getProtocol();
        String remoteAddr = servletRequest.getRemoteAddr();
        int remotePort = servletRequest.getRemotePort();
        Map<String, String[]> parametres = servletRequest.getParameterMap();
        String contextPath = servletRequest.getContextPath();
        String servletPath = servletRequest.getServletPath();
        String pathInfo = servletRequest.getPathInfo();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>")
                .append("<div>protocol: ").append(protocol).append("</div>")
                .append("<div>remoteAddr: ").append(remoteAddr).append("</div>")
                .append("<div>remotePort: ").append(remotePort).append("</div>")
                .append("<div>contextPath: ").append(contextPath).append("</div>")
                .append("<div>servletPath: ").append(servletPath).append("</div>")
                .append("<div>pathInfo: ").append(pathInfo).append("</div>");

        parametres.entrySet().stream().forEach(e -> {
            sb.append("<div>param: ").append(e.getKey()).append("</div>");
        });
        sb.append("</body></html>");
        Writer writer = servletResponse.getWriter();
        writer.write(sb.toString());
    }

    public void doPost(HttpServletRequest servletRequest,
                       HttpServletResponse servletResponse) throws ServletException, IOException {
        doGet(servletRequest, servletResponse);
    }
}
