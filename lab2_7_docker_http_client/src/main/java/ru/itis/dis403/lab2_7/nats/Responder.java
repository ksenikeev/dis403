package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;

public class Responder {
    public static void main(String[] args) {
        try (Connection nc = Nats.connect("nats://localhost:4222")) {
            Dispatcher dispatcher = nc.createDispatcher((msg) -> {
                System.out.printf("Получен запрос: %s, Ответить: %s%n",
                        new String(msg.getData()), msg.getReplyTo());
                // Отправляем ответ обратно отправителю
                nc.publish(msg.getReplyTo(), "Всегда рады помочь!".getBytes());
            });
            dispatcher.subscribe("help");
            System.out.println("Сервис ожидает запросы на тему 'help'");

            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}