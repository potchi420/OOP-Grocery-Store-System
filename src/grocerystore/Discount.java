package grocerystore;

public abstract class Discount {
    protected double price;

    public abstract double getDiscountPrice();

    public Discount(double price) {
        this.price = price;
    }
}

class PWD extends Discount {
    public PWD(double price){
        super(price);
    }

    @Override
    public double getDiscountPrice() {
        return 0.20 * price;
    }
}

class Bronze extends Discount {
    public Bronze(double price){
        super(price);
    }

    @Override
    public double getDiscountPrice() {
        return 0.05 * price;
    }
}

class Silver extends Discount {
    public Silver(double price) {
        super(price);
    }

    @Override
    public double getDiscountPrice() {
        return 0.10 * price;
    }
}

class Gold extends Discount {
    public Gold(double price){
        super(price);
    }

    @Override
    public double getDiscountPrice() {
        return 0.05 * price;
    }
}
