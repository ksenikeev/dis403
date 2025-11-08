package ru.itis.dis403.lab1_07.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab1_07.model.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    final static Logger logger = LogManager.getLogger(CityRepository.class);

    public List<City> getAll() throws Exception {
        List<City> result = new ArrayList<>();

        Connection connection = DbConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "select id, name from city ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(new City(
                    resultSet.getLong("id"),
                    resultSet.getString("name")));
        }
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public City findById(Long id) throws Exception {
        City result = null;

        Connection connection = DbConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "select id, name, comment from city where id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            result = new City(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("comment")
                    );
        }
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }
}
