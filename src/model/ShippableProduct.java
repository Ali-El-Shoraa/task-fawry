// model/ShippableProduct.java
package model;

public class ShippableProduct extends Product {
    private double shippingCost;

    public ShippableProduct(String name, double price, int quantity, double shippingCost) {
        super(name, price, quantity);
        this.shippingCost = shippingCost;
    }

    // احذف @Override هنا إذا لم تكن تُعيد صياغة دالة من الأب
    public double getShippingCost() {
        return shippingCost;
    }
}
