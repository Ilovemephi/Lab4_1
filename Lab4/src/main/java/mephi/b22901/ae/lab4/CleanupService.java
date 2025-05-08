
package mephi.b22901.ae.lab4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CleanupService {

    
    public void clearAllData() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("TRUNCATE TABLE Delivery_details, Delivery, Wand, Warehouse, Component CASCADE RESTART IDENTITY");

            System.out.println("Все данные успешно удалены. Система готова к новой работе.");

        } catch (SQLException e) {
            System.err.println("Ошибка при полной очистке данных: " + e.getMessage());
        }
    }
}
