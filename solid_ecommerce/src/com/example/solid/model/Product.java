package solid_ecommerce.src.com.example.solid.model;

//S — Single Responsibility Principle (SRP)
//A class should have only one reason to change.

public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
//Handles only product data — nothing else
// Which maintain the first rule of the SOLID principle (SRP)
