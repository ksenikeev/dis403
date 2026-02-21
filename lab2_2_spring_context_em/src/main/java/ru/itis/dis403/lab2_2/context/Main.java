package ru.itis.dis403.lab2_2.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.dis403.lab2_2.context.components.Application;
import ru.itis.dis403.lab2_2.context.config.Config;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        //context.getBean(Application.class).run();
        ((Application)context.getBean("App")).run();

    }
}
