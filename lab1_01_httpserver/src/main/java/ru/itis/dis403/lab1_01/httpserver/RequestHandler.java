package ru.itis.dis403.lab1_01.httpserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler {

    final static Logger logger = LogManager.getLogger(RequestHandler.class);

    public void handle(Socket clientSocket) {
        try {
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
                //break;
            }
            while (true) {
                // Читаем пакет от клиента
                String message = reader.readLine();
                System.out.println(message);
                logger.debug(message);

                if (message.isEmpty()) {
                    logger.debug("end of request header");
                    OutputStream os = clientSocket.getOutputStream();
                    os.write("HTTP/1.1 200 OK\r\n".getBytes());
                    os.write("Content-Type: text/html;charset=UTF-8\r\n".getBytes());
                    os.write("\r\n".getBytes());
                    os.write("<html><body>Hello!</body></html>".getBytes());
                    logger.debug("outputStream" + os);
                    os.flush();
                    logger.debug("outputStream" + os);
                    break;
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            logger.atError().withThrowable(e);
        }

    }

}
