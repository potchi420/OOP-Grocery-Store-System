package grocerystore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileLoaderSystem {
    // base class for file loading
    // this class is not meant to be instantiated
    // it is meant to be extended by other classes
    // it is to individually load seperate files
}

// Customer Loader
class CustomerLoader extends FileLoaderSystem {
    private String[][] customer;
    private final String customerFile = "customer.txt";

    // Load customer data
    private String[][] loadCustomer() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) list.add(new String[]{parts[0].trim(), parts[1].trim()});
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
        return list.toArray(new String[0][0]);
    }

    // Getter for customer data
        public String[][] getCustomer() {
        if (customer == null) customer = loadCustomer();
        return customer;
    }
    // Get customer by name
    public String[] getCustomerByName(String name) {
        for (String[] c : getCustomer()) {
            if (c[0].equalsIgnoreCase(name)) return c;
        }
        return null;
    }
    // Get customer by discount plan
    public String[] getCustomerByDiscountPlan(String discountPlan) {
        for (String[] c : getCustomer()) {
            if (c[1].equalsIgnoreCase(discountPlan)) return c;
        }
        return null;
    }
}

// Inventory Loader
class InventoryLoader extends FileLoaderSystem {
    private String[][] items;
    private final String inventoryFile = "inventory.txt";

    // Load inventory data
    private String[][] loadInventory() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) list.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()});
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
        return list.toArray(new String[0][0]);
    }

    // Getter for inventory data
    public String[][] getItems() {
        if (items == null) items = loadInventory();
        return items;
    }
    // Get item by name
    public String[] getItemByName(String name) {
        for (String[] item : getItems()) {
            if (item[0].equalsIgnoreCase(name)) return item;
        }
        return null;
    }
    // Get item by quantity
    public String[] getItemByQuantity(String quantity) {
        for (String[] item : getItems()) {
            if (item[1].equalsIgnoreCase(quantity)) return item;
        }
        return null;
    }
    
    // Display inventory
    public void displayInventory() {
        System.out.println("Inventory List:");
        System.out.println("-------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "Item", "Quantity", "Price");
        for (String[] item : getItems()) {
            System.out.printf("%-15s %-10s %-10s\n", item[0], item[1], item[2]);
        }
        System.out.println("-------------------------------");
    }

    // Subtract stock from inventory
    public void subtractStock(String itemName, int quantity) {
        for (String[] item : items) {
            if (item[0].equalsIgnoreCase(itemName)) {
                int stock = Integer.parseInt(item[1]);
                if (stock >= quantity) {
                    stock -= quantity;
                    item[1] = String.valueOf(stock);
                    System.out.println("Stock updated for " + itemName + ": " + stock);
                } else {
                    System.out.println("Not enough stock available.");
                }
                return;
            }
        }
        System.out.println("Item not found in inventory.");
    }

    // Write inventory to new updated file
    public void writeInventoryToFile() {
        String filename = this.inventoryFile;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String[] item : items) {
                writer.write(String.join(" ", item));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("File write error: " + e.getMessage());
        }
    }    
}

// Transaction Loader
class TransactionLoader extends FileLoaderSystem {
    private String[][] transactions;
    private final String purchaseFile = "transaction.txt";

    // Load transaction data
    private String[][] loadTransactions() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(purchaseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) list.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()});
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
        return list.toArray(new String[0][0]);
    }

    // Getter for transaction data
    public String[][] getTransactions() {
        if (transactions == null) transactions = loadTransactions();
        return transactions;
    }
    // Get transaction by item name
    public String[] getTransactionByItem(String itemName) {
        for (String[] t : getTransactions()) {
            if (t[0].equalsIgnoreCase(itemName)) return t;
        }
        return null;
    }
}

// Sales Loader
class SalesLoader extends FileLoaderSystem {
    private String[][] sales;
    private final String salesFile = "sales.txt";

    // Load sales data
    private String[][] loadSales() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) list.add(new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()});
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
        return list.toArray(new String[0][0]);
    }

    // Getter for sales data
    public String[][] getSales() {
        if (sales == null) sales = loadSales();
        return sales;
    }
    // Get sale by customer name
    public String[] getSaleByName(String name) {
        for (String[] sale : getSales()) {
            if (sale[0].equalsIgnoreCase(name)) return sale;
        }
        return null;
    }
    // Get sale by total sales
    public String[] getSaleByDate(String date) {
        for (String[] sale : getSales()) {
            if (sale[2].equalsIgnoreCase(date)) return sale;
        }
        return null;
    }
}