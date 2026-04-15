package hei.school.demo.repository;

import hei.school.demo.entity.StockMovement;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StockMovementRepository {
    private final DataSource dataSource;

    public StockMovementRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<StockMovement> findByIngredientAndDate(String id, Instant date) {
        List<StockMovement> movements = new ArrayList<>();
        String sql = "SELECT id, quantity, type FROM stock_movement WHERE id_ingredient = ? AND datetime <= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setTimestamp(2, Timestamp.from(date));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    StockMovement sm = new StockMovement();
                    sm.setId(rs.getString("id"));
                    sm.setQuantity(rs.getDouble("quantity"));
                    sm.setType(rs.getString("type"));
                    movements.add(sm);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL: " + e.getMessage());
        }
        return movements;
    }
}
