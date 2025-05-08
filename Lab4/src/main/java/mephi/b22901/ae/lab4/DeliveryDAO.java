
package mephi.b22901.ae.lab4;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO implements DAO<Delivery> {


    @Override
    public void create(Delivery delivery) {
        String sql = "INSERT INTO Delivery(delivery_date) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(delivery.getDeliveryDate()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении поставки: " + e.getMessage());
        }
    }


    @Override
    public Delivery getById(int id) {
        String sql = "SELECT * FROM Delivery WHERE id_delivery = ?";
        Delivery delivery = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LocalDate date = rs.getDate("delivery_date").toLocalDate();
                delivery = new Delivery(id, date);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении поставки: " + e.getMessage());
        }

        return delivery;
    }


    @Override
    public List<Delivery> getAll() {
        String sql = "SELECT * FROM Delivery";
        List<Delivery> deliveries = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_delivery");
                LocalDate date = rs.getDate("delivery_date").toLocalDate();
                deliveries.add(new Delivery(id, date));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка поставок: " + e.getMessage());
        }

        return deliveries;
    }


    @Override
    public void update(Delivery delivery) {
        String sql = "UPDATE Delivery SET delivery_date = ? WHERE id_delivery = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(delivery.getDeliveryDate()));
            pstmt.setInt(2, delivery.getIdDelivery());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении поставки: " + e.getMessage());
        }
    }

 
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Delivery WHERE id_delivery = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении поставки: " + e.getMessage());
        }
    }
}