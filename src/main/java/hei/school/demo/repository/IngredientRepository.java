package hei.school.demo.repository;

import hei.school.demo.entity.Ingredient;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepository {
    private final DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT id, name, unit_price FROM ingredient";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                ingredients.add(new Ingredient(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("unit_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL: " + e.getMessage());
        }
        return ingredients;
    }
}
