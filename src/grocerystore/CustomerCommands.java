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
            System.out.print(
                  """
                          ========================================
                                      Customer System
                          ========================================
                            1. Register Customer
                            2. Remove Customer
                            3. Edit Customer Name
                            4. Edit Customer Status
                            5. Display Customers
                            6. Save and Exit
                          ----------------------------------------
                            Enter your choice: """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerCustomerFlow();
                    break;
                case 2:
                    removeCustomerFlow();
                    break;
                case 3:
                    editCustomerNameFlow();
                    break;
                case 4:
                    editCustomerStatusFlow();
                    break;
                case 5:
                    customer.displayCustomerFlow();
                    break;
                case 6:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerCustomerFlow() {
        System.out.print("How many customer/s to register? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter customer variables as: name, status seperated by a comma");
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
        customer.saveToFile(DEFAULT_FILE);
    }

    private void removeCustomerFlow() {
        System.out.print("Enter customer name to remove: ");
        String removeName = scanner.nextLine().trim();
        System.out.print("Enter customer's status: ");
        String  status = scanner.nextLine().trim();
        customer.removeCustomer(removeName, status);
        customer.saveToFile(DEFAULT_FILE);
    }

    private void editCustomerNameFlow() {
        System.out.print("Enter customer name to edit: ");
        String editName = scanner.nextLine().trim();
        System.out.print("Enter customer's new name: ");
        String  newName = scanner.nextLine().trim();
        customer.editCustomerName(editName, newName);
        customer.saveToFile(DEFAULT_FILE);
    }

    private void editCustomerStatusFlow() {
        System.out.print("Enter customer name to edit: ");
        String editName = scanner.nextLine().trim();
        System.out.print("Enter customer's new status: ");
        String  newStatus = scanner.nextLine().trim();
        customer.editCustomerStatus(editName, newStatus);
        customer.saveToFile(DEFAULT_FILE);
    }


    private void saveAndExit() {
        customer.saveToFile(DEFAULT_FILE);
        System.out.println("Exiting...");
    }
}