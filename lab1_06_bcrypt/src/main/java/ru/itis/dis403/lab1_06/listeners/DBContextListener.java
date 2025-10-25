package ru.itis.dis403.lab1_06.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.itis.dis403.lab1_06.repository.DbConnection;

@WebListener
public class DBContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DbConnection.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DbConnection.destroy();
    }
}
