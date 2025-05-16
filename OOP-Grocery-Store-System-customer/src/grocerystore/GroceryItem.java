package grocerystore;
public class GroceryItem extends Item {
    public GroceryItem(String name, int quantity, double price) {
        super(name, quantity, price);
    }

    @Override
    public void display() {
        System.out.printf("%s: %d units @ $%.2f each\n", getName(), getQuantity(), getPrice());
    }
}
