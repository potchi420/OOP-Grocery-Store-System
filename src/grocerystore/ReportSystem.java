package grocerystore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;


public class ReportSystem {

    protected String filename = "SalesReport.txt";
    protected String itemReportFile = "ItemSoldReport.txt";

    // Enum for report period types
    private enum PeriodType { DAILY, WEEKLY, MONTHLY, YEARLY, OVERALL }

    // Inventory display (unchanged)
    public void displayInventory() {
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

    // Method to generate sales report
    private void generateSalesReport(PeriodType type, String reportTitle) {
        SalesLoader salesLoader = new SalesLoader();
        String[][] sales = salesLoader.getSales();

        Map<String, List<String[]>> groupedSales = new HashMap<>();

        for (String[] entry : sales) {
            if (entry.length < 3) continue;
            String name = entry[0].trim();
            String amount = entry[1].trim();
            String timestamp = entry[2].trim();
            String dateStr = timestamp.contains("T") ? timestamp.split("T")[0] : timestamp.split(" ")[0];

            String key = getGroupingKey(type, dateStr);
            groupedSales.putIfAbsent(key, new ArrayList<>());
            groupedSales.get(key).add(new String[]{name, amount});
        }

        List<String> keys = new ArrayList<>(groupedSales.keySet());
        if (type != PeriodType.OVERALL) Collections.sort(keys, Collections.reverseOrder());

        FileWriterSystem fileWriterSystem = new FileWriterSystem();
        StringBuilder reportContent = new StringBuilder();
        reportContent.append(reportTitle).append(":\n");

        if (type == PeriodType.OVERALL) {
            double total = 0;
            for (String[] sale : groupedSales.getOrDefault("ALL", Collections.emptyList())) {
                try { total += Double.parseDouble(sale[1]); } catch (NumberFormatException ignored) {}
            }
            reportContent.append(String.format("Total Sales: %.2f\n", total));
            for (String[] sale : groupedSales.getOrDefault("ALL", Collections.emptyList())) {
                reportContent.append(String.format("%s, %s\n", sale[0], sale[1]));
            }
        } else {
            for (String key : keys) {
                double total = 0;
                for (String[] sale : groupedSales.get(key)) {
                    try { total += Double.parseDouble(sale[1]); } catch (NumberFormatException ignored) {}
                }
                reportContent.append(String.format("%s Total Sales: %.2f\n", key, total));
                for (String[] sale : groupedSales.get(key)) {
                    reportContent.append(String.format("%s, %s\n", sale[0], sale[1]));
                }
                reportContent.append("\n");
            }
        }

        fileWriterSystem.writeToReportingFiles(filename, reportContent.toString());
        System.out.println(reportTitle + " written to " + filename);
    }

    // Helper to get grouping key based on period type
    private String getGroupingKey(PeriodType type, String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            switch (type) {
                case DAILY:
                    return date.toString();
                case WEEKLY:
                    WeekFields weekFields = WeekFields.ISO;
                    int week = date.get(weekFields.weekOfWeekBasedYear());
                    int year = date.get(weekFields.weekBasedYear());
                    return String.format("%d-W%02d", year, week);
                case MONTHLY:
                    return String.format("%d-%02d", date.getYear(), date.getMonthValue());
                case YEARLY:
                    return String.valueOf(date.getYear());
                case OVERALL:
                    return "ALL";
            }
        } catch (Exception e) {
            // fallback for malformed dates
            return "UNKNOWN";
        }
        return "UNKNOWN";
    }

    // Public methods for each report type
    public void getDailySales()    { generateSalesReport(PeriodType.DAILY,   "Daily Report"); }
    public void getWeeklySales()   { generateSalesReport(PeriodType.WEEKLY,  "Weekly Report"); }
    public void getMonthlySales()  { generateSalesReport(PeriodType.MONTHLY, "Monthly Report"); }
    public void getYearlySales()   { generateSalesReport(PeriodType.YEARLY,  "Yearly Report"); }
    public void getOverallSales()  { generateSalesReport(PeriodType.OVERALL, "Overall Sales Report"); }


    // Method to generate item sold report
    private void generateItemSoldReport(PeriodType type, String reportTitle) {
        TransactionLoader transactionLoader = new TransactionLoader();
        String[][] transactions = transactionLoader.getTransactions();

        Map<String, List<String[]>> groupedItems = new HashMap<>();

        for (String[] entry : transactions) {
            if (entry.length < 3) continue;
            String itemName = entry[0].trim();
            String quantity = entry[1].trim();
            String timestamp = entry[2].trim();
            String dateStr = timestamp.contains("T") ? timestamp.split("T")[0] : timestamp.split(" ")[0];

            String key = getGroupingKey(type, dateStr);
            groupedItems.putIfAbsent(key, new ArrayList<>());
            groupedItems.get(key).add(new String[]{itemName, quantity});
        }

        List<String> keys = new ArrayList<>(groupedItems.keySet());
        if (type != PeriodType.OVERALL) Collections.sort(keys, Collections.reverseOrder());

        FileWriterSystem fileWriterSystem = new FileWriterSystem();
        StringBuilder reportContent = new StringBuilder();
        reportContent.append(reportTitle).append(":\n");

        if (type == PeriodType.OVERALL) {
            int total = 0;
            for (String[] item : groupedItems.getOrDefault("ALL", Collections.emptyList())) {
                try { total += Integer.parseInt(item[1]); } catch (NumberFormatException ignored) {}
            }
            reportContent.append(String.format("Total Items Sold: %d\n", total));
            for (String[] item : groupedItems.getOrDefault("ALL", Collections.emptyList())) {
                reportContent.append(String.format("%s, %s\n", item[0], item[1]));
            }
        } else {
            for (String key : keys) {
                int total = 0;
                for (String[] item : groupedItems.get(key)) {
                    try { total += Integer.parseInt(item[1]); } catch (NumberFormatException ignored) {}
                }
                reportContent.append(String.format("%s Total Items Sold: %d\n", key, total));
                for (String[] item : groupedItems.get(key)) {
                    reportContent.append(String.format("%s, %s\n", item[0], item[1]));
                }
                reportContent.append("\n");
            }
        }

        fileWriterSystem.writeToReportingFiles(itemReportFile, reportContent.toString());
        System.out.println(reportTitle + " written to " + itemReportFile);
    }

    // Public methods for each item sold report type
    public void getDailyItemSold()    { generateItemSoldReport(PeriodType.DAILY,   "Daily Item Sold Report"); }
    public void getWeeklyItemSold()   { generateItemSoldReport(PeriodType.WEEKLY,  "Weekly Item Sold Report"); }
    public void getMonthlyItemSold()  { generateItemSoldReport(PeriodType.MONTHLY, "Monthly Item Sold Report"); }
    public void getYearlyItemSold()   { generateItemSoldReport(PeriodType.YEARLY,  "Yearly Item Sold Report"); }
    public void getOverallItemSold()  { generateItemSoldReport(PeriodType.OVERALL, "Overall Item Sold Report"); }
}
