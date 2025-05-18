package grocerystore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CustomerSystem {
    private List<Customer> customers;

    public CustomerSystem() {
        this.customers = new ArrayList<>();
    }

    public void registerCustomer(String name, String status) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                System.out.println("Customer with name '" + name + "' already exists.");
                return;
            }
        }
        // If the loop finishes without finding a customer with the same name, create a new one
        customers.add(new MemberCustomer(name, status));
        System.out.println("New customer registered: " + name + " with the status of " + status);
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("customers don't exist.");
            return;
        }
        CustomerLoader customerLoader = new CustomerLoader();
        String[][] customer = customerLoader.getCustomer();
        System.out.println("-------------------------------");
        System.out.printf("%-15s %-10s \n", "Item", "Status");
        for (String[] customers : customer) {
            System.out.printf("%-15s %-10s %-10s\n", customers[0], customers[1]);
        }
        System.out.println("-------------------------------");
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Customer customer : customers) {
                writer.write(customer.getName() + " " + customer.getStatus() + "\n");
            }
            System.out.println("Customers successfully saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("No existing inventory file found, starting fresh.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            customers.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    String status = parts[1];
                    customers.add(new MemberCustomer(name, status));
                }
            }
            System.out.println("Customers loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading customers, starting fresh: " + e.getMessage());
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}