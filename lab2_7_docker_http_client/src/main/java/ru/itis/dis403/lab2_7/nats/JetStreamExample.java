package ru.itis.dis403.lab2_7.nats;

import io.nats.client.*;
import io.nats.client.api.*;
import java.io.IOException;
import java.time.Duration;

public class JetStreamExample {
    public static void main(String[] args) {
        try (Connection nc = Nats.connect("nats://localhost:4222")) {

            JetStreamManagement jsm = nc.jetStreamManagement();
            JetStream js = nc.jetStream();

            String streamName = "my_events";
            String subject = "events.>";

            try {
                StreamConfiguration streamConfig = StreamConfiguration.builder()
                        .name(streamName)
                        .subjects(subject)
                        .storageType(StorageType.Memory)
                        .build();
                jsm.addStream(streamConfig);
                System.out.println("Stream created: " + streamName);
            } catch (JetStreamApiException e) {
                System.out.println("Stream already exists.");
            }

            // 1. Создаём Pull Consumer (если его нет)
            String consumerName = "my_pull_consumer";
            try {
                ConsumerConfiguration consumerConfig = ConsumerConfiguration.builder()
                        .durable(consumerName) // для устойчивости
                        .build();
                jsm.addOrUpdateConsumer(streamName, consumerConfig);
                System.out.println("Consumer '" + consumerName + "' is ready.");
            } catch (JetStreamApiException e) {
                System.out.println("Consumer already exists.");
            }

            // 2. Публикуем несколько сообщений
            for (int i = 1; i <= 5; i++) {
                js.publish("events." + i, ("Message #" + i).getBytes());
            }

            // 3. Получаем ConsumerContext и вычитываем сообщения
            ConsumerContext consumerContext = nc.getConsumerContext(streamName, consumerName);

            // 4. Запрашиваем до 10 сообщений (ваш исправленный код)
            try (FetchConsumer fetchConsumer = consumerContext.fetchMessages(10)) {
                Message msg;
                while ((msg = fetchConsumer.nextMessage()) != null) {
                    System.out.printf("Received: %s%n", new String(msg.getData()));
                    msg.ack(); // Подтверждаем обработку
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}