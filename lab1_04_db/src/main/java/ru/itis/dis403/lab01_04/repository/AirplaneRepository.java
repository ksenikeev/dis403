package ru.itis.dis403.lab01_04.repository;

import ru.itis.dis403.lab01_04.model.Airplane;
import ru.itis.dis403.lab01_04.service.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneRepository {

    public List<Airplane> findAll() {

        List<Airplane> airplanes = new ArrayList<>();

        try {
            Connection connection = DbConnection.getConnection();
            String sql = "select airplane_code, " +
                    " model -> 'ru' as model_ru, " +
                    " range, speed " +
                    " from bookings.airplanes_data";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airplane airplane = new Airplane();
                    airplane.setCode(resultSet.getString("airplane_code"));
                    airplane.setModel(resultSet.getString("model_ru"));
                    airplane.setRange(resultSet.getInt("range"));
                    airplane.setSpeed(resultSet.getInt("speed"));
                    airplanes.add(airplane);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airplanes;
    }

}
