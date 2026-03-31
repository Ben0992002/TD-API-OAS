package hei.school.demo.entity;

public record Ingredient(
        Long id,
        String name,
        String category,
        double price
) {}