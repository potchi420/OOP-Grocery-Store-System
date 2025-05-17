package grocerystore;

public class Cart {
    private String[][] cart = new String[100][2];
    private int cartSize = 0;

    public String[][] getCart() {
        String[][] cartCopy = new String[cartSize][2];
        for (int i = 0; i < cartSize; i++) {
            cartCopy[i][0] = cart[i][0];
            cartCopy[i][1] = cart[i][1];
        }
        return cartCopy;
    }

    public int getCartSize() {
        return cartSize;
    }

    public void addToCart(String itemName, int quantity) {
        cart[cartSize][0] = itemName;
        cart[cartSize][1] = String.valueOf(quantity);
        cartSize++;
    }

    public void updateCartItemQuantity(String itemName, int newQuantity) {
        for (int i = 0; i < cartSize; i++) {
            if (cart[i][0].equalsIgnoreCase(itemName)) {
                cart[i][1] = String.valueOf(newQuantity);
                System.out.println("Updated " + itemName + " quantity to " + newQuantity);
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    public void removeItemFromCart(String itemName) {
        for (int i = 0; i < cartSize; i++) {
            if (cart[i][0].equalsIgnoreCase(itemName)) {
                for (int j = i; j < cartSize - 1; j++) {
                    cart[j] = cart[j + 1];
                }
                cartSize--;
                System.out.println("Removed " + itemName + " from cart.");
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    public void clearCart() {
        cart = new String[100][2];
        cartSize = 0;
    }

    public void viewCart() {
        if (cartSize == 0) {
            System.out.println("Your cart is empty.");
            System.out.println("--------------------------------");
            System.out.println();
            return;
        }
        System.out.println("Your Cart:");
        for (int i = 0; i < cartSize; i++) {
            System.out.println("- " + cart[i][0] + " x " + cart[i][1]);
        }
    }
}