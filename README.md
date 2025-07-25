# 🛍️ E-Commerce Terminal Application (Java)

This is a Java-based console application that simulates a simple **E-Commerce checkout system**.  
It supports multiple customers, different product types, and basic cart and checkout operations.

---

## 📦 Features

- Add products with options:
  - Expirable
  - Shippable (with weight)
- View cart contents.
- Proceed to checkout with shipping cost and receipt generation.
- Switch to a new customer and start a new order.
- Product quantity validation and balance deduction.
- Clear cart after successful checkout.

---

## 🧾 How It Works

1. The program asks for the cashier's name.
2. A new customer is created by entering their name and initial balance.
3. A few predefined products are initialized:
   - Cheese (expirable + shippable)
   - Biscuits (expirable + shippable)
   - TV (shippable)
   - Scratch Card (simple product)
4. A sample order is placed with 2 biscuits.
5. The system provides a menu:

===== E-Commerce Menu =====
1- Add a product to cart
2- View cart
3- Proceed to checkout
4- New Order (Switch Customer)
5- Exit

vbnet
Copy
Edit

- **Option 1:** Prompts the user to create a product manually and specify quantity.
- **Option 2:** Displays all items currently in the cart.
- **Option 3:** Performs checkout — verifies stock, prints shipment notice (if needed), calculates shipping, deducts balance, prints receipt, and clears the cart.
- **Option 4:** Starts a new order with a new customer.
- **Option 5:** Exits the program.

---

## ▶️ How to Run the Program

Make sure you're in the project directory and your Java compiler is ready.

### Option 1: Using `run.bat` (recommended on Windows)
Run the following command in the terminal:

```bash
.\run
This assumes you have a file named run.bat that contains:

bat
Copy
Edit
java controller.Main
Option 2: Manually compile and run
If you prefer running manually:

bash
Copy
Edit
javac controller/Main.java
java controller.Main
📁 Project Structure
arduino
Copy
Edit
.
├── controller/
│   └── Main.java
├── model/
│   └── Product.java, Cart.java, CartItem.java, ...
├── service/
│   └── CheckoutService.java, ShippingService.java
├── interfaces/
│   └── Shippable.java
├── run.bat
✅ Example Flow
bash
Copy
Edit
Enter cashier name: John
Enter customer name: Alice
Enter initial balance: 1000
===== E-Commerce Menu =====
1- Add a product to cart
...
🛠️ Requirements
Java JDK 8 or later

Terminal or command prompt

🔚 Notes
The cart is automatically cleared after each successful checkout.

Products created are not saved globally between customers unless predefined.

Product quantities are reduced after each checkout based on what was purchased.
