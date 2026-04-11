package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;

public class AsyncSubscriber {
    public static void main(String[] args) {
        String subject = "time.us";

        try (Connection nc = Nats.connect("nats://localhost:4222")) {
            // Создаем диспетчер для асинхронной обработки
            Dispatcher dispatcher = nc.createDispatcher((msg) -> {
                System.out.printf("Тема: %s, Сообщение: %s%n",
                        msg.getSubject(), new String(msg.getData()));
            });

            // Подписываемся
            dispatcher.subscribe(subject);
            System.out.println("Ожидание сообщений на тему: " + subject);

            // Держим приложение запущенным
            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}