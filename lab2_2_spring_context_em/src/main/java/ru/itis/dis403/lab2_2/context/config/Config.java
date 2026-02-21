package ru.itis.dis403.lab2_2.context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.dis403.lab2_2.context.components.MarketService;
import ru.itis.dis403.lab2_2.context.model.Market;

@Configuration
@ComponentScan("ru.itis.dis403.lab2_2.context.components")
public class Config {

    @Bean
    public MarketService getService() {
        Market market = new Market();
        return new MarketService(market);
    }
}
