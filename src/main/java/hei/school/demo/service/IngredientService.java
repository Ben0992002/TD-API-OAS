package hei.school.demo.service;

import hei.school.demo.entity.Ingredient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {
    private final List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> saveAll(List<Ingredient> toSave) {
        ingredients.addAll(toSave);
        return toSave;
    }

    public List<Ingredient> getAll() {
        return ingredients;
    }
}