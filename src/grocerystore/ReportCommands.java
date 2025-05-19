package grocerystore;
import java.util.Scanner;

public class ReportCommands {
    
    ReportSystem reportSystem = new ReportSystem();
    private Scanner scanner = new Scanner(System.in);

    // Method to display the menu
    public void start() {
        System.out.println("Welcome to the Grocery Report System!");
        runMenuLoop();
    }

    private void runMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.print(
                    """
                            ========================================
                                        Report System
                            ========================================
                              1. Check Inventory
                              2. Sales Report
                              3. Sold Items Report
                              4. Exit
                            ----------------------------------------
                              Enter your choice: """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Inventory Report: ");
                    reportSystem.displayInventory();
                    System.out.println("        ");
                    break;
                case 2:
                    System.out.println("Sales Report: ");
                    displaySalesReport();
                    System.out.println("        ");
                    break;
                case 3: 
                    System.out.println("Sold Items Report: ");
                    displayItemSoldReport();
                    System.out.println("        ");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    System.out.println("        ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("        ");
            }
        }
    }

    // Method to display the sales report
    public void displaySalesReport() {
        System.out.print(
                    """
                            ========================================
                                         Sales Report 
                            ========================================
                              1. Total Daily Sales
                              2. Total Weekly Sales
                              3. Total Monthly Sales
                              4. Total Yearly Sales
                              5. Total Sales
                              6. Exit
                            ----------------------------------------
                              Enter your choice: """);

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Daily Sales: ");
                reportSystem.getDailySales();
                System.out.println("        ");
                break;
            case 2:
                System.out.println("Weekly Sales: ");
                reportSystem.getWeeklySales();
                System.out.println("        ");
                break;
            case 3:
                System.out.println("Monthly Sales: ");
                reportSystem.getMonthlySales();
                System.out.println("        ");
                break;
            case 4:
                System.out.println("Yearly Sales: ");
                reportSystem.getYearlySales();
                System.out.println("        ");
                break;
            case 5:
                System.out.println("Overall Sales: ");
                reportSystem.getOverallSales();
                System.out.println("        ");
                break;
            case 6:
                System.out.println("Exiting...");
                System.out.println("        ");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to display the item sold report
    public void displayItemSoldReport() {
        System.out.print(
            """
                    ========================================
                                Items Sold Report 
                    ========================================
                      1. Items Sold Daily 
                      2. Items Sold Weekly
                      3. Items Sold Monthly
                      4. Items Sold Yearly 
                      5. Total Sold Items
                      6. Exit
                    ----------------------------------------
                      Enter your choice: """);

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Daily sold items: ");
                reportSystem.getDailyItemSold();
                System.out.println("        ");
                break;
            case 2:
                System.out.println("Weekly sold items: ");
                reportSystem.getWeeklyItemSold();
                System.out.println("        ");
                break;
            case 3:
                System.out.println("Monthly sold items: ");
                reportSystem.getMonthlyItemSold();
                System.out.println("        ");
                break;
            case 4:
                System.out.println("Yearly sold items: ");
                reportSystem.getYearlyItemSold();
                System.out.println("        ");
                break;
            case 5:
                System.out.println("Overall sold items: ");
                reportSystem.getOverallItemSold();
                System.out.println("        ");
            case 6:
                System.out.println("Exiting...");
                System.out.println("        ");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                System.out.println("        ");
        }
    }

    // Placeholder methods for sales reports
    public void getWeeklySales() {}
    public void getMonthlySales() {}
    public void getYearlySales() {}
    public void getOverallSales() {}

    // Placeholder methods for item sold reports
    public void getDailyItemSold() {}
    public void getWeeklyItemSold() {}
    public void getMonthlyItemSold() {} 
    public void getYearlyItemSold() {}
    public void getOverallItemSold() {}

}
