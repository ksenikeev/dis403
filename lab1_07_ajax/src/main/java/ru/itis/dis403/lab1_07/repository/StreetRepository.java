package ru.itis.dis403.lab1_07.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab1_07.model.City;
import ru.itis.dis403.lab1_07.model.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StreetRepository {

    final static Logger logger = LogManager.getLogger(StreetRepository.class);

    public List<Street> getAll() throws Exception {
        List<Street> result = new ArrayList<>();

        Connection connection = DbConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "select id, name, city_id from street ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(new Street(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("city_id")));
        }
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public List<Street> findByCityId(Long cityId) throws Exception {
        List<Street> result = new ArrayList<>();

        Connection connection = DbConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                "select id, name, city_id from street where city_id = ? ");
        statement.setLong(1, cityId);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.add(new Street(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("city_id")));
        }
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

}
