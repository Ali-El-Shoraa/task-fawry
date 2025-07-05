package service;

import model.*;
import java.util.List;
import java.util.ArrayList;
import interfaces.Shippable;

public class CheckoutService {
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.err.println("Cart is empty.");
            return;
        }

        List<CartItem> items = cart.getItems();
        List<Shippable> shippables = new ArrayList<>();
        double subtotal = 0;

        for (CartItem item : items) {
            Product product = item.getProduct();

            if (product.getQuantity() < item.getQuantity()) {
                System.err.println("Product out of stock: " + product.getName());
                return;
            }

            if (product.isExpired()) {
                System.err.println("Product expired: " + product.getName());
                return;
            }

            subtotal += item.getTotalPrice();
            product.decreaseQuantity(item.getQuantity());

            // أضف كل وحدة من المنتجات الشحّانة
            if (product.requiresShipping() && product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippables.add((Shippable) product);
                }
            }
        }

        double shippingCost = shippingService.getShippingCost(shippables);
        double total = subtotal + shippingCost;

        // تحقق من الرصيد قبل الشحن
        if (customer.getBalance() < total) {
            System.out.println("Subtotal: " + subtotal);
            System.out.println("Shipping: " + shippingCost);
            System.out.println("Total: " + total);
            System.out.println("Customer Balance: " + customer.getBalance());

            System.err.println("Insufficient balance.");
            return;
        }

        // 💵 Payment
        customer.deduct(total);

        // ✅ طباعة المنتجات القابلة للشحن والشحنة بعد الدفع
        if (!shippables.isEmpty()) {
            System.out.println("Products requiring shipping:");
            for (Shippable s : shippables) {
                System.out.println("- " + s.getName() + " (" + s.getWeight() + "kg)");
            }

            shippingService.ship(shippables);
            System.out.println("-------------------------\n");
        }

        // 🧾 Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "        " + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal         " + subtotal);
        System.out.println("Shipping         " + shippingCost);
        System.out.println("Amount           " + total);
        System.out.println("Customer balance after payment: " + customer.getBalance());
    }
}


// package service;

// import model.*;

// import java.util.List;
// import java.util.ArrayList;

// import interfaces.Shippable;

// public class CheckoutService {
//     private  ShippingService shippingService = new ShippingService();

//     public void checkout(Customer customer, Cart cart) {
//         if (cart.isEmpty()) {
//             System.err.println("Cart is empty.");
//             return;
//         }

//         List<CartItem> items = cart.getItems();
//         List<Shippable> shippables = new ArrayList<>();
//         double subtotal = 0;

//         for (CartItem item : items) {
//             Product product = item.getProduct();

//             if (product.getQuantity() < item.getQuantity()) {
//                 System.err.println("Product out of stock: " + product.getName());
//                 return;
//             }

//             if (product.isExpired()) {
//                 System.err.println("Product expired: " + product.getName());
//                 return;
//             }

//             subtotal += item.getTotalPrice();
//             product.decreaseQuantity(item.getQuantity());

//             if (product.requiresShipping() && product instanceof Shippable) {
//                 shippables.add((Shippable) product);
//             }
//         }

//         double shippingCost = shippingService.getShippingCost(shippables);
//         double total = subtotal + shippingCost;

//         if (customer.getBalance() < total) {
//                 System.out.println("Subtotal: " + subtotal);
//                 System.out.println("Shipping: " + shippingCost);
//                 System.out.println("Total: " + total);
//                 System.out.println("Customer Balance: " + customer.getBalance());

//                 System.err.println("Insufficient balance.");
//                 return;
//         }

//         // Show shipment
//         if (!shippables.isEmpty()) {
//             shippingService.ship(shippables);
//         }

//         // Payment
//         customer.deduct(total);

//         // Print receipt
//         System.out.println("\n");
//         System.out.println("** Checkout receipt **");
//         for (CartItem item : items) {
//             System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "        " + item.getTotalPrice());
//         }
//         System.out.println("----------------------");
//         System.out.println("Subtotal         " + subtotal);
//         System.out.println("Shipping         " + shippingCost);
//         System.out.println("Amount           " + total);
//         System.out.println("Customer balance after payment: " + customer.getBalance());
//     }
// }
