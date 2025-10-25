package ru.itis.dis403.lab1_06.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab1_06.repository.DbConnection;

@WebListener
public class DBContextListener implements ServletContextListener {

    final static Logger logger = LogManager.getLogger(DBContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("contextInitialized");
        try {
            DbConnection.init();
        } catch (ClassNotFoundException e) {
            logger.atError().withThrowable(e).log();
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("contextDestroyed");
        DbConnection.destroy();
    }
}
