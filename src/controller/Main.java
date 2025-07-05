package controller;

import model.*;
import service.*;
import java.util.Scanner;

import interfaces.Shippable;

    public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // المنتجات الثابتة
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.4);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 10);
        Product scratchCard = new Product("Scratch Card", 50, 20);

        // اسم الكاشير
        String cashierName;
        System.out.print("Enter cashier name: ");
        while (true) {
            cashierName = scanner.nextLine().trim();
            if (!cashierName.isEmpty() && cashierName.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.print("Invalid name. Please enter letters only: ");
            }
        }

        CashierInfo cashierInfo = new CashierInfo(cashierName);
        CheckoutService checkoutService = new CheckoutService();

        // ✅ بداية الطلب الأول
        Customer customer = createCustomer(scanner);
        Cart cart = new Cart();
        cart.addProduct(biscuits, 2); // اختبار

        checkoutService.checkout(customer, cart);
        System.out.println(cashierInfo);

        while (true) {
            System.out.println("\n===== E-Commerce Menu =====");
            System.out.println("1- Add a product to cart");
            System.out.println("2- View cart");
            System.out.println("3- Proceed to checkout");
            System.out.println("4- New Order (Switch Customer)");
            System.out.println("5- Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Product product = createProduct(scanner);
                    if (product == null) break;

                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    if (qty > product.getQuantity()) {
                        System.out.println("Only " + product.getQuantity() + " available. Cannot add more.");
                        break;
                    }

                    cart.addProduct(product, qty);
                    System.out.println("Product added to cart.");
                    break;

                case "2":
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Cart Contents:");
                        for (CartItem item : cart.getItems()) {
                            Product p = item.getProduct();
                            System.out.println(item.getQuantity() + "x " + p.getName() +
                                    " | Price: " + p.getPrice() +
                                    (p.requiresShipping() ? " | Weight: " + ((Shippable) p).getWeight() + "kg" : "") +
                                    (p.isExpired() ? " | Expirable" : ""));
                        }
                    }
                    break;

                case "3":
                    if (cart.isEmpty()) {
                        System.out.println("Cannot checkout. Cart is empty.");
                        break;
                    }
                    checkoutService.checkout(customer, cart);
                    System.out.println(cashierInfo);
                     cart = new Cart(); 
                    break;

             

                case "4":
                    customer = createCustomer(scanner);
                    cart = new Cart();
                    System.out.println("🆕 New order started for customer: " + customer.getName());
                    break;

                       case "5":
                    System.out.println("Exiting...");
                    promptToExit();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Customer createCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();

        double balance = 0;
        while (true) {
            System.out.print("Enter initial balance: ");
            try {
                balance = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }

        return new Customer(name, balance);
    }

    private static Product createProduct(Scanner scanner) {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter stock quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // ✅ فحص إجابة expirable
            boolean expirable;
            while (true) {
                System.out.print("Is the product expirable? (yes/no): ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    expirable = true;
                    break;
                } else if (input.equals("no")) {
                    expirable = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }

            // ✅ فحص إجابة shippable
            boolean shippable;
            while (true) {
                System.out.print("Is the product shippable? (yes/no): ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    shippable = true;
                    break;
                } else if (input.equals("no")) {
                    shippable = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }

            double weight = 0;
            if (shippable) {
                System.out.print("Enter weight in kg: ");
                weight = Double.parseDouble(scanner.nextLine());
            }

            if (expirable && shippable)
                return new ExpirableShippableProduct(name, price, quantity, weight);
            if (expirable)
                return new Product(name, price, quantity);
            if (shippable)
                return new ShippableProduct(name, price, quantity, weight);

            return new Product(name, price, quantity);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            return null;
        }
    }

    private static void promptToExit() {
        System.out.println("\nPress ENTER to exit...");
        new Scanner(System.in).nextLine();
    }
}
