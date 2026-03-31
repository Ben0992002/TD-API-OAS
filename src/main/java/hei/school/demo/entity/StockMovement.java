package hei.school.demo.entity;

public class StockMovement {
    private String id;
    private double quantity;
    private String type;

    public StockMovement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
