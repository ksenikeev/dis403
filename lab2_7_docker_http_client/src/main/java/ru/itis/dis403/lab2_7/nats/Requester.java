package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;

import java.time.Duration;

public class Requester {
    public static void main(String[] args) {
        try (Connection nc = Nats.connect("nats://147.45.199.55:4222")) {
            // Отправляем запрос и ждем ответ
            Message reply = nc.request("help", "Нужна помощь!".getBytes(), Duration.ofSeconds(2));

            if (reply != null) {
                System.out.println("Ответ получен: " + new String(reply.getData()));
            } else {
                System.out.println("Ответ не получен.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
