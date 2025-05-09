package grocerystore;

public class Discount {
    public double pwd_senior_discount (double price) {
        double discountAttained = 0.20 * price;
        return price - discountAttained;
    }

    public double bronzeDiscount (double price) {
        double discountAttained = 0.05 * price;
        return price - discountAttained;
    }
    public double silverDiscount (double price) {
        double discountAttained = 0.10 * price;
        return price - discountAttained;
    }

    public double goldDiscount (double price) {
        double discountAttained = 0.15 * price;
        return price - discountAttained;
    }
}
