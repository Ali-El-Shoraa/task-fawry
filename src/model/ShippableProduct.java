
package model;

public class ShippableProduct extends Product {
    private double shippingCost;

    public ShippableProduct(String name, double price, int quantity, double shippingCost) {
        super(name, price, quantity);
        this.shippingCost = shippingCost;
    }


    public double getShippingCost() {
        return shippingCost;
    }
}
