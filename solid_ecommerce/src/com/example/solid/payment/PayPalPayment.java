package solid_ecommerce.src.com.example.solid.payment;

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid" + amount + " using PayPal.");
    }
}
