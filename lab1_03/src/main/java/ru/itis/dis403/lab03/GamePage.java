package ru.itis.dis403.lab03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/game")
public class GamePage extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GamePage.class);

    private final Map<String, GameState> gamers = new HashMap<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug(request.getServletPath());

        String uid = UUID.randomUUID().toString();
        GameState gameState = new GameState();
        gamers.put(uid, gameState);

        request.setAttribute("table", gameState.getTable());
        request.setAttribute("uid", uid);

        request.getRequestDispatcher("/game.ftlh")
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String row = request.getParameter("row");
        String column = request.getParameter("column");
        String uid = request.getParameter("uid");

        logger.debug("uid " + uid + ", row " + row + ", column " + column);

        GameState gameState = gamers.get(uid);

        List<Row> table = gameState.getTable();
        Row trow = table.get(Integer.parseInt(row) - 1);
        trow.setT("k.jpg");

        request.setAttribute("table", table);
        request.setAttribute("uid", uid);

        request.getRequestDispatcher("/game.ftlh")
                .forward(request, response);
    }
}
