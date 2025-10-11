package ru.itis.dis403.lab01_04;

import java.sql.*;

public class TestDBPreparedStatment {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");

            Connection connection =
                    DriverManager.getConnection(
                            // адрес БД , имя пользователя, пароль
                            "jdbc:postgresql://localhost:5432/demo","postgres","passwd");

            String sql = "select * from bookings.airplanes_data where airplane_code = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "35X");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.print(resultSet.getString("airplane_code") + ";");
                System.out.print(resultSet.getString("model") + ";");
                System.out.print(resultSet.getString("range") + ";");
                System.out.println(resultSet.getString("speed"));
            }

            resultSet.close();

            statement.close();
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
