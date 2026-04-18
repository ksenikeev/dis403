package ru.itis.dis403.lab2_9.httpclient.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class ImageService {

    // TODO реализовать пул клиентских подключений

    private List<String> imgList = new ArrayList<>();

    public List<String> getImgList() {
        return imgList;
    }

    public void processImage(byte[] imageBytes) {
        String outputPath = UUID.randomUUID().toString();
        String serverUrl = "http://127.0.0.1:5000/resize";

        String contentType = "image/jpeg";
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverUrl))
                    .timeout(Duration.ofSeconds(30))
                    .header("Content-Type", contentType)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
                    .build();

            CompletableFuture<HttpResponse<byte[]>> futureResponse =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());

            CompletableFuture<Void> processingFuture = futureResponse
                    .thenAccept(response -> {
                        int statusCode = response.statusCode();
                        System.out.println("Статус ответа: " + statusCode);
                        if (statusCode == 200) {
                            imgList.add(Base64.getEncoder().encodeToString(response.body()));
                        } else {
                            String errorBody = new String(response.body());
                            throw new CompletionException("Сервер вернул ошибку " + statusCode + ": " + errorBody, null);
                        }
                    })
                    // Обработка ошибок на любом этапе цепочки
                    .exceptionally(ex -> {
                        // Распаковываем CompletionException, если он есть
                        Throwable cause = (ex instanceof CompletionException && ex.getCause() != null)
                                ? ex.getCause() : ex;
                        System.err.println("Ошибка при обработке изображения: " + cause.getMessage());
                        if (cause instanceof IOException) {
                            System.err.println("Проверьте доступность файлов и права доступа.");
                        } else if (cause instanceof InterruptedException) {
                            System.err.println("Операция была прервана.");
                            Thread.currentThread().interrupt();
                        } else {
                            System.err.println("Возможно, сервер недоступен или неверный URL: " + serverUrl);
                        }
                        cause.printStackTrace();
                        return null; // возвращаем null, чтобы цепочка завершилась без значения
                    });

            // Ожидаем завершения (обработка ошибок внутри exceptionally не даёт упасть main)
            processingFuture.join();
            System.out.println("Программа завершена.");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
