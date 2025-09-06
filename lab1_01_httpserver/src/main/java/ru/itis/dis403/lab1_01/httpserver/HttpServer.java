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
        logger.info("start HttpServer");
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                // Ожидаем подключения клиента
                Socket clientSocket = serverSocket.accept();
                // Дождались клиента
                // Поток для чтения данных от клиента
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                // Читаем пакет от клиента
                String lineOne = reader.readLine();
                System.out.println(lineOne);
                logger.debug(lineOne);
                String[] components = lineOne.split(" ");
                String resource = components[1];
                if (resource.equals("/shutdown")) {
                    logger.info("server stopped by client");
                    break;
                }
                while (true) {
                    // Читаем пакет от клиента
                    String message = reader.readLine();
                    System.out.println(message);
                    logger.debug(message);

                    if (message.isEmpty()) {
                        OutputStream os = clientSocket.getOutputStream();
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type: text/html;charset=UTF-8\r\n".getBytes());
                        os.write("\r\n".getBytes());
                        os.write("<html><body>Hello!</body></html>".getBytes());
                        os.flush();
                        break;
                    }
                }
                clientSocket.close();

            }
            serverSocket.close();
        } catch (IOException e) {
            logger.atError().withThrowable(e);
            throw new RuntimeException(e);
        }

    }
}
