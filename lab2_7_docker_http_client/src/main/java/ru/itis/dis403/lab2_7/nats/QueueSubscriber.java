package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;

public class QueueSubscriber {
    public static void main(String[] args) {
        String queueName = "workers";
        String subject = "task.process";

        try (Connection nc = Nats.connect("nats://localhost:4222")) {
            Dispatcher dispatcher = nc.createDispatcher((msg) -> {
                System.out.printf("[Worker] %s обработал: %s%n",
                        Thread.currentThread().getName(), new String(msg.getData()));
            });

            // Подписываемся на тему с указанием имени очереди
            dispatcher.subscribe(subject, queueName);
            System.out.println("Worker запущен в очереди: " + queueName);

            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
