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

public class InventorySystem {
    private List<Item> items;

    public InventorySystem() {
        this.items = new ArrayList<>();
    }

    public void addItem(String name, int quantity, double price) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println(quantity + " units added to existing item: " + name);
                return;
            }
        }
        items.add(new GroceryItem(name, quantity, price));
        System.out.println("New item added: " + name + " (" + quantity + " units at Php " + String.format("%.2f", price) + ")");
    }

    public void removeItem(String name, int quantity) {
        for (Item item : new ArrayList<>(items)) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getQuantity() <= quantity) {
                    items.remove(item);
                    System.out.println("Item removed completely: " + name);
                } else {
                    item.setQuantity(item.getQuantity() - quantity);
                    System.out.println(quantity + " units removed from: " + name);
                }
                return;
            }
        }
        System.out.println("Item not found in inventory: " + name);
    }

    public void editItemPrice(String name, double price) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getPrice() == price)
                {
                    System.out.println("Entered price is the same as the current price.");
                }
                else {
                    item.setPrice(price);
                    System.out.println(name + "'s Item price has been modified to: Php " + price);
                }
                return;
            }
        }
    }

    public void editItemName(String name, String updatedName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getName().equalsIgnoreCase(updatedName))
                {
                    System.out.println("Entered item name is the same as the current item name.");
                }
                else {
                    item.setName(updatedName);
                    System.out.println(name + " has been modified to: " + updatedName);
                }
                return;
            }
        }
    }

    public void editItemQuantity(String name, int quantity) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                if (item.getPrice() == quantity)
                {
                    System.out.println("Entered quantity is the same as the current item quantity.");
                }
                else {
                    item.setPrice(quantity);
                    System.out.println(name + "'s Item quantity has been modified: " + quantity);
                }
                return;
            }
        }
    }
    public void displayInventory() {

        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        InventoryLoader inventoryLoader = new InventoryLoader();
        String[][] inventory = inventoryLoader.getItems();
        Arrays.sort(inventory, Comparator.comparingInt(a -> Integer.parseInt(a[1])));
        System.out.println("-------------------------------");
        System.out.printf("%-15s %-10s %-10s\n", "Item", "Quantity",  "Price");
        for (String[] item : inventory) {
            System.out.printf("%-15s %-10s %-10s\n", item[0], item[1], "Php " + item[2]);
        }
        System.out.println("-------------------------------");
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Item item : items) {
                writer.write(item.getName() + " " + item.getQuantity() + " " + item.getPrice() + "\n");
            }
            System.out.println("Inventory successfully saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("No existing inventory file found, starting fresh.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            items.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 3) {
                    String name = parts[0];
                    int qty = Integer.parseInt(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    items.add(new GroceryItem(name, qty, price));
                }
            }
            System.out.println("Inventory loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading inventory, starting fresh: " + e.getMessage());
        }
    }

    public List<Item> getItem() {
        return items;
    }
}