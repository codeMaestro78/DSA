package solid_ecommerce.src.com.example.solid.payment;

//O — Open/Closed Principle (OCP)
//
//Software entities should be open for extension, but closed for modification.
//
//        We can add new payment methods or shipping methods without touching existing code.
public interface Payment {
    void pay(double amount);
}
