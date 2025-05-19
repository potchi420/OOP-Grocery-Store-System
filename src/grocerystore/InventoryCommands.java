package grocerystore;
import java.util.Scanner;
//import java.util.List;

public class InventoryCommands {
    private static final String DEFAULT_FILE = "inventory.txt";

    private InventorySystem inventory;
    private Scanner scanner;

    public InventoryCommands() {
        this.inventory = new InventorySystem();
        this.scanner = new Scanner(System.in);
        inventory.loadFromFile(DEFAULT_FILE);
    }

    public void start() {
        runMenuLoop();
    }
    private void runMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.print(
                    """
                            ========================================
                                     Inventory System
                            ========================================
                              1. Add Items
                              2. Remove Item
                              3. Edit Item
                              4. Display Inventory
                              5. Save amd Exit 
                            ----------------------------------------
                              Enter your choice: """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItemsFlow();
                    System.out.println("      ");
                    break;
                case 2:
                    removeItemFlow();
                    System.out.println("      ");
                    break;
                case 3:
                    editItemFlow();
                    System.out.println("      ");
                    break;
                case 4:
                    inventory.displayInventory();
                    System.out.println("      ");
                    break;
                case 5:
                    saveAndExit();
                    running = false;
                    System.out.println("      ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addItemsFlow() {
        System.out.print("How many distinct items to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter each item as: name quantity price seperated by spaces");
        for (int i = 0; i < count; i++) {
            System.out.printf("Item %d: ", i + 1);
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Invalid format. Please use name quantity price");
                i--; continue;
            }
            try {
                String name = parts[0];
                int qty = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                inventory.addItem(name, qty, price);
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
        System.out.print(
                    """    
                            ========================================
                                         Edit Items
                            ========================================
                              1. Edit Item Price
                              2. Edit Item Name
                              3. Edit Item Quantity
                            ----------------------------------------
                              Enter your choice: """);

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