package hei.school.demo.entity;

public class Ingredient {
    private String id;
    private String name;
    private double unitPrice;

    public Ingredient() {}
    public Ingredient(String id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}