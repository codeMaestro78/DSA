package solid_ecommerce.src.com.example.solid.shipping;

//L — Liskov Substitution Principle (LSP)
//Subclasses should be replaceable by their parent type without breaking the program.


public interface ShippingMethod {
    double calculateCost(double weight);

    String getName();

}
