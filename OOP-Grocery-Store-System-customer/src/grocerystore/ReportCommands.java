package grocerystore;
import java.util.Scanner;

public class ReportCommands {
    
    ReportSystem reportSystem = new ReportSystem();
    private Scanner scanner = new Scanner(System.in);

    // Method to display the menu
    public void start() {
        System.out.println("Welcome to the Grocery Report System!");
        runMenuLoop();
        scanner.close();
    }

    private void runMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Check Inventory");
            System.out.println("2. Sales Report");  
            System.out.println("3. Sold Items Report");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Inventory Report: ");
                    reportSystem.displayInventory();
                    break;
                case 2:
                    System.out.println("Sales Report: ");
                    displaySalesReport();
                    break;
                case 3: 
                    System.out.println("Sold Items Report: ");
                    displayItemSoldReport();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display the sales report
    public void displaySalesReport() {
        System.out.println("What would you like to check?");
        System.out.println("1. Total Daily Sales");
        System.out.println("2. Total Weekly Sales");
        System.out.println("3. Total Monthly Sales");
        System.out.println("4. Total Yearly Sales");
        System.out.println("5. Total Sales");
        System.out.println("6. Exit");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Daily Sales: ");
                reportSystem.getDailySales();
                break;
            case 2:
                System.out.println("Weekly Sales: ");
                reportSystem.getWeeklySales();
                break;
            case 3:
                System.out.println("Monthly Sales: ");
                reportSystem.getMonthlySales();
                break;
            case 4:
                System.out.println("Yearly Sales: ");
                reportSystem.getYearlySales();
                break;
            case 5:
                System.out.println("Overall Sales: ");
                reportSystem.getOverallSales();
            case 6:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to display the item sold report
    public void displayItemSoldReport() {
        System.out.println("What would you like to check?");
        System.out.println("1. Item sold Daily");
        System.out.println("2. Item sold Weekly");
        System.out.println("3. Item sold Monthly");
        System.out.println("4. Item sold Yearly");
        System.out.println("5. Total sold Items");
        System.out.println("6. Exit");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Daily sold items: ");
                reportSystem.getDailyItemSold();
                break;
            case 2:
                System.out.println("Weekly sold items: ");
                reportSystem.getWeeklyItemSold();
                break;
            case 3:
                System.out.println("Monthly sold items: ");
                reportSystem.getMonthlyItemSold();
                break;
            case 4:
                System.out.println("Yearly sold items: ");
                reportSystem.getYearlyItemSold();
                break;
            case 5:
                System.out.println("Overall sold items: ");
                reportSystem.getOverallItemSold();
            case 6:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
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
