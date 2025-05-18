package grocerystore;
import java.util.Scanner;

public class CustomerCommands {
    private static final String DEFAULT_FILE = "customer.txt";

    private CustomerSystem customer;
    private Scanner scanner;

    public CustomerCommands() {
        this.customer = new CustomerSystem();
        this.scanner = new Scanner(System.in);
        customer.loadFromFile(DEFAULT_FILE);
    }

    public void start() {
        runMenuLoop();
    }

    private void runMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Register Customer");
            System.out.println("2. Remove Item");
            System.out.println("3. Edit Item");
            System.out.println("4. Display Inventory");
            System.out.println("5. Save & Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerCustomerFlow();
                    break;
                case 2:
                    removeItemFlow();
                    break;
                case 3:
                    editItemFlow();
                    break;
                case 4:
                    inventory.displayInventory();
                    break;
                case 5:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerCustomerFlow() {
        System.out.print("How many customer/s to to register? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter customer variables as: name status seperated by spaces");
        for (int i = 0; i < count; i++) {
            System.out.printf("Customer %d: ", i + 1);
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid format. Please use name status");
                i--; continue;
            }
            try {
                String name = parts[0];
                String status = parts[1];
                custom.addItem(name, status);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please re-enter this item.");
                i--;
            }
        }
    }

    private void removeItemFlow() {
        System.out.print("Enter item name to remove: ");
        String removeName = scanner.nextLine().trim();
        System.out.print("Enter quantity to remove: ");
        int removeQty = scanner.nextInt();
        scanner.nextLine();
        inventory.removeItem(removeName, removeQty);
    }

    private void editItemFlow() {
        System.out.println("Select an option to modify:");
        System.out.println("1. Edit Item Price");
        System.out.println("2. Edit Item Name");
        System.out.println("3. Edit Item Quantity");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter item name to be modified: ");
                String itemName_PriceChange = scanner.nextLine().trim();
                System.out.print("Enter desired updated price: ");
                double priceChange = scanner.nextDouble();
                scanner.nextLine();
                inventory.editItemPrice(itemName_PriceChange, priceChange);
                break;
            case 2:
                System.out.print("Enter item name to be modified: ");
                String itemName_NameChange = scanner.nextLine().trim();
                System.out.print("Enter desired updated name: ");
                String nameChange = scanner.nextLine();
                inventory.editItemName(itemName_NameChange, nameChange);
                break;
            case 3:
                System.out.print("Enter item name to be modified: ");
                String itemName_QuantityChange = scanner.nextLine().trim();
                System.out.print("Enter desired updated quantity: ");
                int quantityChange = scanner.nextInt();
                scanner.nextLine();
                inventory.editItemQuantity(itemName_QuantityChange, quantityChange);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }

    private void saveAndExit() {
        inventory.saveToFile(DEFAULT_FILE);
        System.out.println("Exiting. . .");
    }
}