package ru.itis.dis403.lab1_06.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    final static Logger logger = LogManager.getLogger(DbConnection.class);

    private static DataSource dataSource;

    public static void init() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/lab06");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setConnectionTimeout(50000); // в миллисекундах
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);

        logger.debug("dataSource создался успешно");
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (dataSource != null) {
            logger.debug("отдаем подключение");
            return dataSource.getConnection();
        } else {
            try {
                init();
                return dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void destroy() {
        ((HikariDataSource)dataSource).close();
    }

}
