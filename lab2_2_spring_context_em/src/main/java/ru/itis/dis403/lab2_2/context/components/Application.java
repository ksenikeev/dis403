package ru.itis.dis403.lab2_2.context.components;

import org.springframework.stereotype.Component;
import ru.itis.dis403.lab2_2.context.model.Category;
import ru.itis.dis403.lab2_2.context.model.Order;
import ru.itis.dis403.lab2_2.context.model.Product;
import java.math.BigDecimal;

@Component("App")
public class Application {

    private final MarketService service;

    public Application(MarketService service) {
        this.service = service;
    }

    public void run() {
        try {
            service.doOrder(new Order(
                    new Product("Компьютер", "0001", Category.PC, BigDecimal.valueOf(50000)),
                    10, "Клиент 1"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
