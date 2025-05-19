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
        System.out.println("Available in Inventory.");
        loadSystem.displayInventory();
    }

    // Method to add item to cart (uses Cart's addToCart)
    public void addItemToCart(String input, Scanner scanner) {
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
    }

    // Method to checkout and calculate total
    public void checkout(String customerName) {
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
    }

    // Method to update the System
    public void updateSystem(String customerName) {
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
    }

    // Method to Display Receipt
    public void displayReceipt() {
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
    }

    public void applyDiscount(String customerName) {
        String[][] customers = customerLoader.getCustomer();

        // First, check if the customer is in the list
        for (String[] customer : customers) {
            if (customer[0].equalsIgnoreCase(customerName)) {
                String discountPlan = customer[1];
                double discountAmount;

                switch (discountPlan.toLowerCase()) {
                    case "pwd":
                        discountAmount = new PWD(getTotal()).getDiscountPrice();
                        break;
                    case "bronze":
                        discountAmount = new Bronze(getTotal()).getDiscountPrice();
                        break;
                    case "silver":
                        discountAmount = new Silver(getTotal()).getDiscountPrice();
                        break;
                    case "gold":
                        discountAmount = new Gold(getTotal()).getDiscountPrice();
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

        // If not found in the list, but entered as "PWD", apply PWD discount
        if (customerName.equalsIgnoreCase("PWD")) {
            double discountAmount = new PWD(getTotal()).getDiscountPrice();
            setTotal(getTotal() - discountAmount);
            System.out.println("Customer Name: " + customerName);
            System.out.println("Discount Plan: PWD");
            System.out.println("Discount applied: $" + discountAmount);
            System.out.println("New Total: $" + getTotal());
            System.out.println("-------------------------------");
        }
    }

    // Method to update new inventory
    public void updateInventory() {
        String[][] cart = getCart();
        int cartSize = getCartSize();
        for (int i = 0; i < cartSize; i++) {
            String itemName = cart[i][0];
            int quantity = Integer.parseInt(cart[i][1]);
            loadSystem.subtractStock(itemName, quantity);
        }
    }
}
