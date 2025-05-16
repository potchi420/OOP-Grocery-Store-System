package grocerystore;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReceiptSystem extends Cart {

    private double total = 0.0; // Total amount for the cart
    FileWriterSystem writerSystem = new FileWriterSystem();
    InventoryLoader loadSystem = new InventoryLoader();
    CustomerLoader customerLoader = new CustomerLoader();

    // Encapsulation for total
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    // Method to display inventory 
    public void displayInventory() {
        try {
            System.out.println("Available in Inventory.");
            loadSystem.displayInventory();
        } catch (Exception e) {
            System.out.println("Error displaying inventory: " + e.getMessage());
        }
    }

    // Method to add item to cart (uses Cart's addToCart)
    public void addItemToCart(String input, Scanner scanner) {
        try {
            String[] item = loadSystem.getItemByName(input);
            if (item == null) {
                System.out.println("Item not found in inventory.");
                return;
            }

            String itemName = item[0];
            int stock = Integer.parseInt(item[1]);

            if (stock <= 0) {
                System.out.println("Item is out of stock.");
                return;
            }

            System.out.print("Enter quantity for " + itemName + ": ");
            int quantity;
            try {
                quantity = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
                return;
            }

            if (quantity > stock) {
                System.out.println("Not enough stock available.");
                return;
            }

            addToCart(itemName, quantity);

            System.out.println("Added " + quantity + " of " + itemName + " to cart.");
            System.out.println("-------------------------------");
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing stock or quantity: " + nfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding item to cart: " + e.getMessage());
        }
    }

    // Method to checkout and calculate total
    public void checkout(String customerName) {
        try {
            if (getCartSize() == 0) {
                System.out.println("Your cart is empty. Cannot proceed to checkout.");
                return;
            }
            
            displayReceipt();

            System.out.println("Total: $" + getTotal());
            System.out.println("-------------------------------");

            // Apply discount if applicable
            applyDiscount(customerName);

            // Update the system
            updateSystem(customerName);
            System.out.println("Thank you for shopping with us!");
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }

    // Method to update the System
    public void updateSystem(String customerName) {
        try {
            // Update inventory after checkout
            updateInventory();
            loadSystem.writeInventoryToFile();
            System.out.println("Inventory updated.");

            // Write the cart to a file
            writerSystem.writeTransactionToFile(getCart());
            System.out.println("Receipt saved to transaction.txt.");

            // Write the sales to a file
            writerSystem.writeSalesToFile(customerName, getTotal());
            System.out.println("Sales saved to sales.txt.");

            // Clear the cart after checkout
            clearCart();
            setTotal(0.0);
        } catch (Exception e) {
            System.out.println("Error updating system: " + e.getMessage());
        }
    }

    // Method to Display Receipt
    public void displayReceipt() {
        try {
            System.out.println("Receipt:");
            System.out.println("--------------------------------");
            System.out.println("Items purchased:");
            System.out.printf("%-15s %-10s %-10s\n", "Item", "Quantity", "Price");
            
            setTotal(0.0); // Reset total for new calculation
            String[][] cart = getCart();
            int cartSize = getCartSize();
            for (int i = 0; i < cartSize; i++) {
                String itemName = cart[i][0];
                int quantity = Integer.parseInt(cart[i][1]);
                String[] item = loadSystem.getItemByName(itemName);
                double price = Double.parseDouble(item[2]);
                double itemTotal = price * quantity;
                setTotal(getTotal() + itemTotal);
                System.out.printf("%-15s %-10s %-10s\n",  itemName, quantity, itemTotal);
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error displaying receipt: " + e.getMessage());
        }
    }

    // Method to apply discount based on customer name
    public void applyDiscount(String customerName) {
        try {
            String[][] customers = customerLoader.getCustomer();
            Discount discount = new Discount();

            // Check if customer name is in the list
            for (String[] customer : customers) {
                if (customer[0].equalsIgnoreCase(customerName)) {
                    String discountPlan = customer[1];
                    double discountAmount = 0.0;

                    switch (discountPlan.toLowerCase()) {
                        case "pwd":
                            discountAmount = discount.pwd_senior_discount(getTotal());
                            break;
                        case "bronze":
                            discountAmount = discount.bronzeDiscount(getTotal());
                            break;
                        case "silver":
                            discountAmount = discount.silverDiscount(getTotal());
                            break;
                        case "gold":
                            discountAmount = discount.goldDiscount(getTotal());
                            break;
                        default:
                            System.out.println("Invalid discount plan.");
                            return;
                    }

                    setTotal(getTotal() - discountAmount);
                    System.out.println("Customer Name: " + customerName);
                    System.out.println("Discount Plan: " + discountPlan);
                    System.out.println("Discount applied: $" + discountAmount);
                    System.out.println("New Total: $" + getTotal());
                    System.out.println("-------------------------------");

                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Error applying discount: " + e.getMessage());
        }
    }

    // Method to update new inventory
    public void updateInventory() {
        try {
            String[][] cart = getCart();
            int cartSize = getCartSize();
            for (int i = 0; i < cartSize; i++) {
                String itemName = cart[i][0];
                int quantity = Integer.parseInt(cart[i][1]);
                loadSystem.subtractStock(itemName, quantity);
            }
        } catch (Exception e) {
            System.out.println("Error updating inventory: " + e.getMessage());
        }
    }
}