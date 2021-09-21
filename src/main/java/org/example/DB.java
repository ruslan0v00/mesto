package org.example;

import java.sql.*;

public class DB {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "ruslan";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Adilet Abdurahkmanov");
            System.out.println("connected to the PostgreSQL server successFully.");

        } catch (SQLException a) {
            System.out.println(a.getMessage());
            System.out.println("hlahahahha");
        }
        return connection;

    }

    public static int getUsersCount() {
        String SQL = "select count(*) from users";
        int count = 0;
        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static void addUsers(String name, int age) {
        String SQL = "insert into users(name,age) values(? ,?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void printUsers() {
        String SQL = "select * from users";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {


            while (resultSet.next()) {
                System.out.println(resultSet.getInt("users_id") + " "
                        + resultSet.getString("name") + " "
                        + resultSet.getInt("age"));
                System.out.println("**** id **** name **** age ****");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }}}