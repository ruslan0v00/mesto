package org.example;

import java.sql.*;

public class JDBC {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pass = "ruslan";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("server uspeshno");
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
        return connection;
    }

    public static void getCit() {
        try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                Statement stmt = conn.createStatement()
        ) {
            String sql = "CREATE TABLE city" +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR not null ," +
                    " count VARCHAR not null ) ";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (
                SQLException e) {
            e.printStackTrace(); } }

    public static void getCity() {
        String SQL = "select count(*) from city";
        int count = 0;
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
    }

    public static void getCity1() {
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
        ) {
            String str = "insert into city values(1,'batken','kyrgyzstan')";

            statement.executeQuery(str);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
