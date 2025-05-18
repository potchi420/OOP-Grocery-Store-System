package grocerystore;
public abstract class Customer {
    private String name;
    private String status;

    public Customer(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStatus(String status) {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void display();
}