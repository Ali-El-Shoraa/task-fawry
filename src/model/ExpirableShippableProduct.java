package model;

import interfaces.Shippable;

public class ExpirableShippableProduct extends Product implements Shippable {
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
