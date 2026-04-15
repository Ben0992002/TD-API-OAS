package hei.school.demo.service;

import hei.school.demo.entity.Ingredient;
import hei.school.demo.entity.StockMovement;
import hei.school.demo.repository.IngredientRepository;
import hei.school.demo.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepo;
    private final StockMovementRepository stockRepo;

    public IngredientService(IngredientRepository ingredientRepo, StockMovementRepository stockRepo) {
        this.ingredientRepo = ingredientRepo;
        this.stockRepo = stockRepo;
    }

    public List<Ingredient> getAll() {
        return ingredientRepo.findAll();
    }

    public double getStockAt(String id, Instant t, String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new RuntimeException("Either mandatory query parameter `at` or `unit` is not provided.");
        }
        List<StockMovement> movements = stockRepo.findByIngredientAndDate(id, t);
        double total = 0;
        for (StockMovement m : movements) {
            if ("ENTREE".equals(m.getType())) {
                total += m.getQuantity();
            } else {
                total -= m.getQuantity();
            }
        }
        return total;
    }
}