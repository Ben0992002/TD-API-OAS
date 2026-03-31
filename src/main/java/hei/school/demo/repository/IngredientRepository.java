package hei.school.demo.repository;

import hei.school.demo.entity.Ingredient;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientRepository {

    // On définit les infos de connexion
    private final String url = "jdbc:postgresql://localhost:5432/restaurant";
    private final String user = "restaurant_user";
    private final String password = "my_password";

    // a) La méthode pour récupérer TOUS les ingrédients
    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    // b) La méthode pour récupérer UN ingrédient par son ID
    public Ingredient findById(Long id) {
        String sql = "SELECT * FROM ingredient WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Ingredient(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si non trouvé (le Controller gérera la 404)
    }
}