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
            System.out.println("2. Save & Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerCustomerFlow();
                    break;
                case 2:
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
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid format. Please use name status");
                i--; continue;
            }
            try {
                String name = parts[0];
                String status = parts[1];
                customer.registerCustomer(name, status);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please re-enter this item.");
                i--;
            }
        }
    }


    private void saveAndExit() {
        customer.saveToFile(DEFAULT_FILE);
        System.out.println("Exiting. . .");
    }
}