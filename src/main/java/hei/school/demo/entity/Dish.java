package hei.school.demo.entity;

import java.util.List;
import java.util.ArrayList;

public class Dish {
    private String id;
    private String name;
    private double unitPrice;
    private List<Ingredient> ingredients;

    public Dish() {
        this.ingredients = new ArrayList<>();
    }

    public Dish(String id, String name, double unitPrice, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.ingredients = ingredients;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
}