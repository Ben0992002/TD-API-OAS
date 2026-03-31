package hei.school.demo.service;

import hei.school.demo.entity.Ingredient;
import hei.school.demo.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public Ingredient getById(Long id) {
        return repository.findById(id);
    }
}
