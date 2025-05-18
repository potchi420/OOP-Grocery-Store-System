package grocerystore;
/*
import java.util.*;

public class CustomerManager
{
    private List<MemberCustomer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public CustomerManager()
    {
        customers.add(new MemberCustomer("Bro", "Bronze"));
        customers.add(new MemberCustomer("Raphael", "Gold"));
        customers.add(new MemberCustomer("Chris", "PWD"));
        customers.add(new MemberCustomer("Lance", "Silver"));
        customers.add(new MemberCustomer("Jenny", "Gold"));
    }

    public MemberCustomer findOrRegisterCustomer()
    {
        System.out.print("Are you a new customer? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        if (answer.equals("no"))
        {
            for (MemberCustomer c : customers)
            {
                if (c.getName().equalsIgnoreCase(name))
                {
                    System.out.println("Welcome back, " + name + "! Your tier: " + c.getTier());
                    return c;
                }
            }
            System.out.println("Customer not found. Registering as new customer...");
        }

        MemberCustomer newCustomer = new MemberCustomer(name, "Bronze");
        customers.add(newCustomer);

        System.out.println("Hello, " + name + "! You've been registered with Bronze tier.");
        return newCustomer;
    }

    // getter for all customers if needed for reports
    public List<MemberCustomer> getAllCustomers()
    {
        return customers;
    }
}
*/