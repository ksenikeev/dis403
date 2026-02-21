package ru.itis.dis403.lab2_2.orm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EntityManagerFactory {

    private HikariDataSource dataSource;

    public EntityManagerFactory() throws ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/lab2");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setConnectionTimeout(50000);
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);

        // Сканирование модели
    }

    public EntityManager getEntityManager() {
        try {
            return new EntityManagerImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        dataSource.close();
    }
}
