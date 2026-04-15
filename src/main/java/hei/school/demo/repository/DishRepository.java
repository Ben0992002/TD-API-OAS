package hei.school.demo.repository;

import hei.school.demo.entity.Dish;
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
public class DishRepository {
    private final DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dish> findAll() {
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT id, name, unit_price FROM dish";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getString("id"));
                dish.setName(rs.getString("name"));
                dish.setUnitPrice(rs.getDouble("unit_price"));

                dish.setIngredients(findIngredientsByDishId(dish.getId(), conn));

                dishes.add(dish);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL Dish: " + e.getMessage());
        }
        return dishes;
    }

    private List<Ingredient> findIngredientsByDishId(String dishId, Connection conn) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT i.id, i.name, i.unit_price FROM ingredient i " +
                "JOIN dish_ingredients di ON i.id = di.id_ingredient " +
                "WHERE di.id_dish = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dishId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ingredients.add(new Ingredient(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getDouble("unit_price")
                    ));
                }
            }
        }
        return ingredients;
    }
}