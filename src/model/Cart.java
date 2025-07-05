package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough quantity in stock.");
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
