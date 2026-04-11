package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;

import java.time.Duration;

public class BasicPubSub {
    public static void main(String[] args) {
        String subject = "11-403.messages";

        try (Connection nc = Nats.connect("nats://147.45.199.55:4222")) {

            // Синхронная подписка на тему
            Subscription sub = nc.subscribe(subject);

            // Отправка сообщения
            nc.publish(subject, "Сегодня тема NATS".getBytes());
            System.out.println("Сообщение опубликовано на тему: " + subject);

            // Ожидание сообщения (с таймаутом в 1 секунду)
            Message msg = sub.nextMessage(Duration.ofSeconds(3));
            if (msg != null) {
                String response = new String(msg.getData());
                System.out.println("Получено сообщение: " + response);
            } else {
                System.out.println("Сообщение не получено.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}