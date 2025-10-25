package ru.itis.dis403.lab1_06.repository;

import ru.itis.dis403.lab1_06.model.User;

import java.sql.*;

public class UserRepository {

    public void addUser(User user) throws Exception {
        Connection connection = DbConnection.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statement =
                connection.prepareStatement("select id from nextval('user_seq') as id");
        ResultSet resultSet = statement.getResultSet();
        Long id = null;
        if (resultSet.next()) {
            id = resultSet.getLong("id");
        }
        resultSet.close();
        statement.close();

        statement = connection.prepareStatement(
                "insert into users (id, username, hashpassword) values (?, ?, ?)");
        statement.setLong(1, id);
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getHashPassword());
        int countRow = statement.executeUpdate();
        statement.close();

        statement = connection.prepareStatement(
            "insert into userinfo (id, lastname, firstname, phone) values ( ?, ? , ? , ?)");
        statement.setLong(1, id);
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getPhone());
        countRow = statement.executeUpdate();
        statement.close();

        connection.commit();
        connection.close();
    }

}
