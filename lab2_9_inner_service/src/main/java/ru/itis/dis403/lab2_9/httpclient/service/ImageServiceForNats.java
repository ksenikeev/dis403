package ru.itis.dis403.lab2_9.httpclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceForNats {

    private String natsUrl = "nats://localhost:4222";
    // Описываем тему сообщения в канал
    private String subject = "request.image.mirror";

    // сформировать клиент к брокеру сообщений
    public String processImage(byte[] image) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("service", "mirror");
        requestMap.put("image", Base64.getEncoder().encodeToString(image));

        try (Connection nc = Nats.connect(natsUrl)) {
            System.out.println("connected");

            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(requestMap);
            System.out.println("send JSON: " + jsonRequest.substring(0,30));

            // Отправляем в брокер сообщений сообщение
            Message reply = nc.request(subject, jsonRequest.getBytes(),
                    Duration.ofSeconds(10));

            // Парсим JSON-ответ
            String jsonResponse = new String(reply.getData(), StandardCharsets.UTF_8);
            System.out.println("response " + jsonResponse);

            return jsonResponse;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Запрос прерван");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO описание ошибки
        return "{}";
    }


}
