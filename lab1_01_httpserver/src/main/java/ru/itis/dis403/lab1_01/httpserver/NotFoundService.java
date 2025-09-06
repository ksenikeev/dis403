package ru.itis.dis403.lab1_01.httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class NotFoundService implements IResourceService {
    @Override
    public void service(String method, Map<String, String> params, OutputStream os) throws IOException {
        os.write("HTTP/1.1 200 OK\r\n".getBytes());
        os.write("Content-Type: text/html;charset=UTF-8\r\n".getBytes());
        os.write("\r\n".getBytes());
        os.write("<html><body>404 Page Not Found!</body></html>".getBytes());
    }
}
