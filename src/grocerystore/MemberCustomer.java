package grocerystore;
public class MemberCustomer extends Customer {
    public MemberCustomer(String name, String status) {
        super(name, status);
    }

    @Override
    public void display() {
        System.out.printf(getName() + " : with the status of " + getStatus() +"\n");
    }
}
