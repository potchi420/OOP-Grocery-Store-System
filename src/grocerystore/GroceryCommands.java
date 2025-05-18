package grocerystore;

import java.util.Scanner;

public class GroceryCommands
{
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.print(
                    """
                            ========================================
                                     Grocery Store System
                            ========================================
                              1. Admin Commands
                              2. Report Commands
                              3. Customer Commands
                              4. Register/Find Customer Commands
                              5. Exit
                            ----------------------------------------
                              Enter your choice:""");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    new InventoryCommands().start();
                    break;
                case 2:
                    new ReportCommands().start();
                    break;
                case 3:
                    new ReceiptCommands().start();
                    break;
                case 4:

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    start = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
