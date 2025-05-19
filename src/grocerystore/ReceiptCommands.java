package grocerystore;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReceiptCommands {

    ReceiptSystem receipt = new ReceiptSystem();
    private Scanner scanner = new Scanner(System.in);
    
    public void start() {
        System.out.println("Welcome to the Grocery Store Dear Customer!");
        runMenuLoop();
    }

    // Method to display the menu and handle user input
    private void runMenuLoop() {
        boolean running = true;
        while (running) {
            try {
                System.out.print(
                    """
                            ========================================
                                     Shopping System
                            ========================================
                              1. Browse Available Items
                              2. Add Item to Cart
                              3. View Cart
                              4. Edit Cart
                              5. Checkout
                              6. Exit
                            ----------------------------------------
                              Enter your choice: """);

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // clear invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Available Items: ");
                        receipt.displayInventory();
                        System.out.println("           ");
                        break;
                    case 2:
                        System.out.println("Enter the item name to add to cart:");
                        String itemName = scanner.nextLine().toLowerCase().trim();
                        receipt.addItemToCart(itemName, scanner);
                        System.out.println("           ");
                        break;
                    case 3:
                        receipt.viewCart();
                        System.out.println("           ");
                        break;  
                    case 4:
                        System.out.println("Enter the item name to edit in cart:");
                        String editItemName = scanner.nextLine().toLowerCase().trim();
                        editCartItem(editItemName, scanner);
                        System.out.println("           ");
                        break;
                    case 5:
                        System.out.println("Do you want to checkout or continue to browsing?");
                        System.out.println("1. Checkout");
                        System.out.println("2. Continue Browsing");
                        System.out.print("Your choice: ");
                        int checkoutChoice;
                        try {
                            checkoutChoice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException ime) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();
                            break;
                        }
                        if (checkoutChoice == 2) {
                            System.out.println("Continuing to browse...");
                            break;
                        }
                        System.out.println("Proceeding to checkout...");
                        System.out.println("Enter name for Membership/PWD Discount. (Press ENTER to skip): ");
                        String customerName = scanner.nextLine();
                        if (customerName.isEmpty()) {
                            System.out.println("No name entered. Proceeding without discount.");
                            customerName = "No Name";
                        } else {
                            System.out.println("Customer Name: " + customerName);
                        }
                        receipt.checkout(customerName);
                        System.out.println("           ");
                        break;
                    case 6:
                        System.out.println("Thank you for shopping with us! Goodbye!");
                        running = false;
                        System.out.println("           ");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        System.out.println("           ");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // clear buffer
            }
        }
    }

    // Method to display edit cart item
    public void editCartItem(String itemName, Scanner scanner) {
        System.out.println("Editing item in cart: " + itemName);
        System.out.println("Choose what to edit: ");
        System.out.println("1. Change Quantity");
        System.out.println("2. Remove Item");
        System.out.println("3. Exit editing");
        System.out.print("Your choice: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        switch (choice) {
            case 1:
                System.out.println("Enter new quantity for " + itemName + ": ");
                int newQuantity;
                try {
                    newQuantity = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    return;
                }
                if (newQuantity <= 0) {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                    return;
                }
                receipt.updateCartItemQuantity(itemName, newQuantity);
                break;
            case 2:
                System.out.println("Removing item from cart: " + itemName);
                receipt.removeItemFromCart(itemName);
                break;
            case 3:
                System.out.println("Exiting edit mode.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}