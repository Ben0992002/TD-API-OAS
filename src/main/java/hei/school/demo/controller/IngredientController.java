package hei.school.demo.controller;

import hei.school.demo.entity.Ingredient;
import hei.school.demo.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok("Welcome " + name);
    }

    @PostMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> create(@RequestBody List<Ingredient> toCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(toCreate));
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAll(@RequestHeader(value = "Accept", required = false) String accept) {
        if (accept == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if (!accept.contains("application/json")) return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        return ResponseEntity.ok(service.getAll());
    }
}