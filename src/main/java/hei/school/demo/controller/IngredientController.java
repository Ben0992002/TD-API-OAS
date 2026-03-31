package hei.school.demo.controller;

import hei.school.demo.entity.Ingredient;
import hei.school.demo.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    // Injection du Service pour respecter la séparation des responsabilités
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    // GET /ingredients
    @GetMapping
    public List<Ingredient> getAll() {
        return service.getAll();
    }

    // GET /ingredients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Ingredient ing = service.getById(id);
        if (ing == null) {
            return ResponseEntity.status(404).body("Ingredient.id=" + id + " is not found");
        }
        return ResponseEntity.ok(ing);
    }

    // GET /ingredients/{id}/stock (Point d'entrée du contexte TD5)
    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(
            @PathVariable Long id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit) {

        if (service.getById(id) == null) {
            return ResponseEntity.status(404).body("Ingredient.id=" + id + " is not found");
        }
        if (at == null || unit == null) {
            return ResponseEntity.status(400).body("Either mandatory query parameter `at` or `unit` is not provided.");
        }
        // Valeur par défaut pour l'exercice
        return ResponseEntity.ok("{\"unit\":\"" + unit + "\", \"value\": 10.0}");
    }
}