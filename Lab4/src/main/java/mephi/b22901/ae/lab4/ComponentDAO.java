
package mephi.b22901.ae.lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentDAO implements DAO<Component> {


    @Override
public void create(Component component) {
    String sql = "INSERT INTO Component(component_type, component_name) VALUES (?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, component.getComponentType());
        pstmt.setString(2, component.getComponentName());
        pstmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Ошибка при добавлении компонента: " + e.getMessage());
    }
}


    @Override
    public Component getById(int id) {
        String sql = "SELECT * FROM Component WHERE id_component = ?";
        Component component = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("component_type");
                String name = rs.getString("component_name");
                component = new Component(id, type, name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении компонента: " + e.getMessage());
        }

        return component;
    }


    @Override
    public List<Component> getAll() {
        String sql = "SELECT * FROM Component";
        List<Component> components = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_component");
                String type = rs.getString("component_type");
                String name = rs.getString("component_name");
                components.add(new Component(id, type, name));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка компонентов: " + e.getMessage());
        }

        return components;
    }


    @Override
    public void update(Component component) {
        String sql = "UPDATE Component SET component_type = ?, component_name = ? WHERE id_component = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, component.getComponentType());
            pstmt.setString(2, component.getComponentName());
            pstmt.setInt(3, component.getIdComponent());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении компонента: " + e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Component WHERE id_component = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении компонента: " + e.getMessage());
        }
    }


    public Component getByTypeAndName(String type, String name) {
        String sql = "SELECT * FROM Component WHERE component_type = ? AND component_name = ?";
        Component component = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_component");
                component = new Component(id, type, name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при поиске компонента по типу и имени: " + e.getMessage());
        }

        return component;
    }
}