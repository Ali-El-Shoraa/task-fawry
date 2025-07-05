package controller;

import model.*;
import service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // إنشاء المنتجات
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.4);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, 0.7);
        Product tv = new ShippableProduct("TV", 5000, 3, 10);
        Product scratchCard = new Product("Scratch Card", 50, 20);

        Customer customer = new Customer("Ali", 1000);

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


        Cart cart = new Cart();
        // cart.addProduct(cheese, 2);
        cart.addProduct(biscuits, 1);
        // cart.addProduct(scratchCard, 1);
        cart.addProduct(tv, 1);

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.checkout(customer, cart);

        System.out.println(cashierInfo);

        promptToExit();
    }


    private static void promptToExit() {
        System.out.println("\nPress ENTER to exit...");
        Scanner scanner = new Scanner(System.in);
  
        scanner.close();
    }
}
