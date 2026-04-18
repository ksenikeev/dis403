package ru.itis.dis403.lab2_9.httpclient.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.Nats;
import ru.itis.dis403.lab2_8.model.Weather;

import java.util.Random;

public class WeatherPublisher {

    public static void main(String[] args) {

        String subject = "Weather";

        try (Connection nc = Nats.connect("nats://147.45.199.55:4222")) {

            while (true) {

                Random random = new Random();
                Weather weather = Weather.builder()
                        .city("Казань")
                        .temp(10. + random.nextDouble() * 2 - 1)
                        .pressure(744 + random.nextDouble() * 4 - 2)
                        .windSpeed(3 + random.nextDouble() * 4 - 2)
                        .windDirection("СЗ").build();

                ObjectMapper mapper = new ObjectMapper();
                byte[] msg = mapper.writeValueAsBytes(weather);

                // Отправка сообщения
                nc.publish(subject, msg);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
