
package mephi.b22901.ae.lab4;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WandDAO implements DAO<Wand> {

    private ComponentDAO componentDAO = new ComponentDAO();


    @Override
    public void create(Wand wand) {
        String sql = "INSERT INTO Wand(owner_name, id_wood, id_core, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, wand.getOwnerName());
            pstmt.setInt(2, wand.getWood().getIdComponent());
            pstmt.setInt(3, wand.getCore().getIdComponent());
            pstmt.setString(4, wand.getStatus());
            pstmt.executeUpdate();


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    wand.setIdWand(rs.getInt(1)); 
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении палочки: " + e.getMessage());
        }
    }


    @Override
    public Wand getById(int id) {
        String sql = "SELECT * FROM Wand WHERE id_wand = ?";
        Wand wand = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String owner = rs.getString("owner_name");
                int woodId = rs.getInt("id_wood");
                int coreId = rs.getInt("id_core");
                String status = rs.getString("status");

                Component wood = componentDAO.getById(woodId);
                Component core = componentDAO.getById(coreId);

                wand = new Wand(id, wood, core);
                wand.setOwnerName(owner);
                wand.setStatus(status);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении палочки: " + e.getMessage());
        }

        return wand;
    }


    @Override
    public List<Wand> getAll() {
        String sql = "SELECT * FROM Wand";
        List<Wand> wands = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_wand");
                String owner = rs.getString("owner_name");
                int woodId = rs.getInt("id_wood");
                int coreId = rs.getInt("id_core");
                String status = rs.getString("status");

                Component wood = componentDAO.getById(woodId);
                Component core = componentDAO.getById(coreId);

                Wand wand = new Wand(id, wood, core);
                wand.setOwnerName(owner);
                wand.setStatus(status);

                wands.add(wand);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка палочек: " + e.getMessage());
        }

        return wands;
    }


    @Override
    public void update(Wand wand) {
        String sql = "UPDATE Wand SET owner_name = ?, id_wood = ?, id_core = ?, status = ? WHERE id_wand = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, wand.getOwnerName());
            pstmt.setInt(2, wand.getWood().getIdComponent());
            pstmt.setInt(3, wand.getCore().getIdComponent());
            pstmt.setString(4, wand.getStatus());
            pstmt.setInt(5, wand.getIdWand());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении палочки: " + e.getMessage());
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Wand WHERE id_wand = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении палочки: " + e.getMessage());
        }
    }


    public List<Wand> getAvailableWands() {
        String sql = "SELECT * FROM Wand WHERE status = 'в наличии'";

        List<Wand> availableWands = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_wand");
                String owner = rs.getString("owner_name");
                int woodId = rs.getInt("id_wood");
                int coreId = rs.getInt("id_core");
                String status = rs.getString("status");

                Component wood = componentDAO.getById(woodId);
                Component core = componentDAO.getById(coreId);

                Wand wand = new Wand(id, wood, core);
                wand.setOwnerName(owner);
                wand.setStatus(status);

                availableWands.add(wand);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении доступных палочек: " + e.getMessage());
        }

        return availableWands;
    }
}
