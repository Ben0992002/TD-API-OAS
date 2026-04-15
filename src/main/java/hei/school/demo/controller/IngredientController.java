package hei.school.demo.controller;

import hei.school.demo.entity.Ingredient;
import hei.school.demo.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return service.getAll();
    }

    @GetMapping("/ingredients/{id}/stock")
    public double getStock(
            @PathVariable String id,
            @RequestParam Instant at,
            @RequestParam String unit) {
        return service.getStockAt(id, at, unit);
    }
}