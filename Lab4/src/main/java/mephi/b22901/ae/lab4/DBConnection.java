
package mephi.b22901.ae.lab4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static final String URL = "jdbc:postgresql://localhost:5432/MagicShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Brateevo11b";
    private static Connection connection;


    private DBConnection() {}


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Соединение с БД установлено.");

            } catch (SQLException e) {
                System.err.println("Ошибка подключения к БД.");
                e.printStackTrace();
            } 
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с БД закрыто.");
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть соединение.");
                e.printStackTrace();
            }
        }
    }
}