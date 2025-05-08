
package mephi.b22901.ae.lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDetailsDAO implements DAO<DeliveryDetails> {

    private DeliveryDAO deliveryDAO = new DeliveryDAO();

    @Override
    public void create(DeliveryDetails detail) {
        String sql = "INSERT INTO Delivery_details(id_delivery, id_component, component_type, component_name, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detail.getDelivery().getIdDelivery());
            pstmt.setInt(2, detail.getIdComponent());
            pstmt.setString(3, detail.getComponentType());
            pstmt.setString(4, detail.getComponentName());
            pstmt.setInt(5, detail.getAmount());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении детали поставки: " + e.getMessage());
        }
    }

    @Override
    public DeliveryDetails getById(int id) {
        String sql = "SELECT * FROM Delivery_details WHERE id_detail = ?";
        DeliveryDetails detail = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int deliveryId = rs.getInt("id_delivery");
                int componentId = rs.getInt("id_component");
                String type = rs.getString("component_type");
                String name = rs.getString("component_name");
                int amount = rs.getInt("amount");

                Delivery delivery = deliveryDAO.getById(deliveryId);

                detail = new DeliveryDetails(id, delivery, componentId, type, name, amount);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении детали поставки: " + e.getMessage());
        }

        return detail;
    }

    public List<DeliveryDetails> getAllForDelivery(int idDelivery) {
        String sql = "SELECT * FROM Delivery_details WHERE id_delivery = ?";
        List<DeliveryDetails> details = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDelivery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idDetail = rs.getInt("id_detail");
                int componentId = rs.getInt("id_component");
                String type = rs.getString("component_type");
                String name = rs.getString("component_name");
                int amount = rs.getInt("amount");

                Delivery delivery = deliveryDAO.getById(idDelivery);

                DeliveryDetails detail = new DeliveryDetails(idDetail, delivery, componentId, type, name, amount);
                details.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении деталей поставки: " + e.getMessage());
        }

        return details;
    }

    @Override
    public List<DeliveryDetails> getAll() {
        String sql = "SELECT * FROM Delivery_details";
        List<DeliveryDetails> details = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idDetail = rs.getInt("id_detail");
                int deliveryId = rs.getInt("id_delivery");
                int componentId = rs.getInt("id_component");
                String type = rs.getString("component_type");
                String name = rs.getString("component_name");
                int amount = rs.getInt("amount");

                Delivery delivery = deliveryDAO.getById(deliveryId);

                DeliveryDetails detail = new DeliveryDetails(idDetail, delivery, componentId, type, name, amount);
                details.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех деталей поставок: " + e.getMessage());
        }

        return details;
    }

    @Override
    public void update(DeliveryDetails detail) {
        String sql = "UPDATE Delivery_details SET id_delivery = ?, id_component = ?, component_type = ?, component_name = ?, amount = ? WHERE id_detail = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detail.getDelivery().getIdDelivery());
            pstmt.setInt(2, detail.getIdComponent());
            pstmt.setString(3, detail.getComponentType());
            pstmt.setString(4, detail.getComponentName());
            pstmt.setInt(5, detail.getAmount());
            pstmt.setInt(6, detail.getIdDetail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении детали поставки: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Delivery_details WHERE id_detail = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении детали поставки: " + e.getMessage());
        }
    }
}