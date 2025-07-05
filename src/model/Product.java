package model;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void decreaseQuantity(int qty) { this.quantity -= qty; }

    public boolean isExpired() { return false; }
    public boolean requiresShipping() { return false; }
    public double getWeight() { return 0.0; }
}
