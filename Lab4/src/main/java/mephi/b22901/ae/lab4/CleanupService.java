
package mephi.b22901.ae.lab4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CleanupService {

    
    public void clearAllData() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("TRUNCATE TABLE Delivery_details, Delivery, Wand, Warehouse, Component CASCADE");
            
            stmt.execute("ALTER SEQUENCE component_id_component_seq RESTART WITH 1");
            stmt.execute("ALTER SEQUENCE delivery_id_delivery_seq RESTART WITH 1");
            stmt.execute("ALTER SEQUENCE delivery_details_id_detail_seq RESTART WITH 1");
            stmt.execute("ALTER SEQUENCE wand_id_wand_seq RESTART WITH 1");

            System.out.println("Все данные успешно удалены. Система готова к новой работе.");

        } catch (SQLException e) {
            System.err.println("Ошибка при полной очистке данных: " + e.getMessage());
        }
    }
}
