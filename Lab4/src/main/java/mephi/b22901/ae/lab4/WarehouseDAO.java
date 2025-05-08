
package mephi.b22901.ae.lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements DAO<Warehouse> {

    private ComponentDAO componentDAO = new ComponentDAO();


    @Override
    public void create(Warehouse warehouse) {
        String sql = "INSERT INTO Warehouse(id_component, amount) VALUES (?, ?)";
        Component component = warehouse.getComponent();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, component.getIdComponent());
            pstmt.setInt(2, warehouse.getAmount());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении на склад: " + e.getMessage());
        }
    }


    @Override
    public Warehouse getById(int id) {
        String sql = "SELECT * FROM Warehouse WHERE id_component = ?";
        Warehouse warehouse = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int amount = rs.getInt("amount");
                Component component = componentDAO.getById(id); 

                warehouse = new Warehouse(component, amount);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных со склада: " + e.getMessage());
        }

        return warehouse;
    }

    @Override
    public List<Warehouse> getAll() {
        String sql = "SELECT * FROM Warehouse";
        List<Warehouse> stock = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_component");
                int amount = rs.getInt("amount");

                Component component = componentDAO.getById(id);
                stock.add(new Warehouse(component, amount));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении всего склада: " + e.getMessage());
        }

        return stock;
    }

    public void updateStock(Component component, int newAmount) {
        String sql = "UPDATE Warehouse SET amount = ? WHERE id_component = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newAmount);
            pstmt.setInt(2, component.getIdComponent());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении склада: " + e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Warehouse WHERE id_component = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении со склада: " + e.getMessage());
        }
    }

    @Override
    public void update(Warehouse warehouse) {
        String sql = "UPDATE Warehouse SET amount = ? WHERE id_component = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, warehouse.getAmount());
            pstmt.setInt(2, warehouse.getComponent().getIdComponent());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении данных на складе: " + e.getMessage());
        }
    }
    
}
