package grocerystore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSystem {
    
    private String transactionsfile = "transaction.txt";
    private String salesfile = "sales.txt";

    // Method to write the cart to a file with present date and time
    // Format: "itemName, quantity, dateandtimestamp"
    // Example: "apple, 2, 2023-10-01 12:00:00"
    public void writeTransactionToFile(String[][] cart) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsfile, true))) {
            for (int i = 0; i < cart.length; i++) {
                if (cart[i][0] != null) {
                    String itemName = cart[i][0];
                    String quantity = cart[i][1];
                    String dateAndTime = java.time.LocalDateTime.now().toString();
                    writer.write(itemName + ", " + quantity + ", " + dateAndTime);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
        
    // Method to write the cart to a file with present date and time
    // Format: "CustomerName, Total Sales, dateandtimestamp"
    // Example: "Chris, 300.00, 2023-10-01 12:00:00"
    public void writeSalesToFile(String customerName, double totalSales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salesfile, true))) {
            String dateAndTime = java.time.LocalDateTime.now().toString();
            writer.write(customerName + ", " + totalSales + ", " + dateAndTime);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }  
}
