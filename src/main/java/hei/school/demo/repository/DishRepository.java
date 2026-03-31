package hei.school.demo.repository;

import hei.school.demo.entity.Dish;
import hei.school.demo.entity.CreateDish;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishRepository {
    private final String url = "jdbc:postgresql://localhost:5432/restaurant";
    private final String user = "restaurant_user";
    private final String password = "my_password";

    // Pour le POST : Sauvegarder un plat et récupérer son ID
    public Dish save(CreateDish createDish) throws SQLException {
        String sql = "INSERT INTO dish (name, category, unit_price) VALUES (?, ?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, createDish.name());
            pstmt.setString(2, createDish.category());
            pstmt.setDouble(3, createDish.unitPrice());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Dish(rs.getLong(1), createDish.name(), createDish.category(), createDish.unitPrice());
            }
        }
        return null;
    }

    // Pour l'erreur 400 : Vérifier si le nom existe déjà
    public boolean existsByName(String name) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) FROM dish WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Pour le GET filtré : Construction dynamique de la requête
    public List<Dish> findByCriteria(Double priceUnder, Double priceOver, String name) {
        List<Dish> dishes = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM dish WHERE 1=1");

        if (priceUnder != null) sql.append(" AND unit_price < ").append(priceUnder);
        if (priceOver != null) sql.append(" AND unit_price > ").append(priceOver);
        if (name != null) sql.append(" AND name ILIKE '%").append(name).append("%'");

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                dishes.add(new Dish(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("unit_price")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return dishes;
    }
}