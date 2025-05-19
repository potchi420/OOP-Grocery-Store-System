package grocerystore;

import java.util.Scanner;

public class GroceryCommands {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.print(
                    """
                            ========================================
                                     Grocery Store System
                            ========================================
                              1. Inventory Commands
                              2. Report Commands
                              3. Shop Commands
                              4. Customer Commands
                              5. Exit
                            ----------------------------------------
                              Enter your choice: """);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    new InventoryCommands().start();
                    System.out.println("      ");
                    break;
                case 2:
                    new ReportCommands().start();
                    System.out.println("      ");
                    break;
                case 3:
                    new ReceiptCommands().start();
                    System.out.println("      ");
                    break;
                case 4:
                    new CustomerCommands().start();
                    System.out.println("      ");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    start = false;
                    System.out.println("      ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
