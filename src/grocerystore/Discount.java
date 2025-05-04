package grocerystore;

public class Discount {
    public double pwd_senior_discount (double price) {
        double discountAttained = 0.20 * price;
        return price - discountAttained;
    }
}
