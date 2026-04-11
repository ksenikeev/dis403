package ru.itis.dis403.lab2_7.nats;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

import java.time.Duration;

public class Publisher {
    public static void main(String[] args) {
        String subject = "11-403.messages";

        try (Connection nc = Nats.connect("nats://147.45.199.55:4222")) {

            // Отправка сообщения
            nc.publish(subject, "Сегодня тема NATS 1".getBytes());
            System.out.println("Сообщение опубликовано на тему: " + subject);

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}