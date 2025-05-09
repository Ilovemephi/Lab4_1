
package mephi.b22901.ae.lab4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/MagicShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Brateevo11b";


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }


    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println(" Соединение с БД закрыто.");
            } catch (SQLException e) {
                System.err.println("Не удалось закрыть соединение: " + e.getMessage());
            }
        }
    }
}