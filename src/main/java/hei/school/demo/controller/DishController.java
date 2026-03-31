package hei.school.demo.controller;

import hei.school.demo.entity.Dish;
import hei.school.demo.entity.CreateDish;
import hei.school.demo.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    // On injecte le Service pour respecter la séparation des responsabilités
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    /**
     * POST /dishes : Création de masse de plats.
     * Le status 400 (nom existant) et 500 (erreurs SQL) sont gérés par
     * le GlobalExceptionHandler via les exceptions lancées par le service.
     */
    @PostMapping
    public ResponseEntity<List<Dish>> createDishes(@RequestBody List<CreateDish> createDishes) throws SQLException {
        List<Dish> savedDishes = service.createAll(createDishes);
        // Retourne 201 Created avec la liste des plats créés
        return ResponseEntity.status(201).body(savedDishes);
    }

    /**
     * GET /dishes : Récupère les plats avec filtrage optionnel.
     */
    @GetMapping
    public List<Dish> getDishes(
            @RequestParam(required = false) Double priceUnder,
            @RequestParam(required = false) Double priceOver,
            @RequestParam(required = false) String name) {
        return service.getAllFiltered(priceUnder, priceOver, name);
    }
}