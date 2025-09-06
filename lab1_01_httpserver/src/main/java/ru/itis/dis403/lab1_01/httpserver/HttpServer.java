package ru.itis.dis403.lab1_01.httpserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 */
public class HttpServer {

    final static Logger logger = LogManager.getLogger(HttpServer.class);

    public static void main(String[] args) {

        new Application().init();

        RequestHandler requestHandler = new RequestHandler();
        logger.info("start HttpServer");
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                // Ожидаем подключения клиента
                Socket clientSocket = serverSocket.accept();
                // Дождались клиента
                new Thread(() -> requestHandler.handle(clientSocket)).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            logger.atError().withThrowable(e);
            throw new RuntimeException(e);
        }
    }
}
