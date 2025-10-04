package ru.itis.dis403.lab1_02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest,
                      HttpServletResponse servletResponse) throws ServletException, IOException {
        String form = """
                <html>
                <body>
                <img src="/lab02/img/ico4.svg"/>
                <form method='post' action='/lab02/test'>
                    <div>
                        <input type='text' name='param1'></input>
                    </div>
                    <div>
                        <input type='submit' value='SEND'></input>
                    </div>
                </form>
                </body>
                </html>
                """;
        servletResponse.getWriter().write(form);
/*
        new TemplateHandler().hanlde("index.html",
                servletResponse.getWriter());
*/
    }
}