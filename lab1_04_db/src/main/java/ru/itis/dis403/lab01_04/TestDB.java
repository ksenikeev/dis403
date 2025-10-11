package ru.itis.dis403.lab01_04;

import java.sql.*;

public class TestDB {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");

            Connection connection =
                    DriverManager.getConnection(
                            // адрес БД , имя пользователя, пароль
                            "jdbc:postgresql://localhost:5432/lab06","postgres","passwd");

            Statement statement = connection.createStatement();
            //Boolean result = statement.execute("create table users(id bigint primary key, name varchar(50))");

            int count = statement.executeUpdate(
                    "insert into users (id,name) values (3,'Misha')  ");


            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users where id= ? ");
            preparedStatement.setLong(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("name"));
            }

            resultSet.close();
            System.out.println(count);
            statement.close();
            connection.close();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
