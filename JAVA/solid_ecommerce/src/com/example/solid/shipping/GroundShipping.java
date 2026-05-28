package solid_ecommerce.src.com.example.solid.shipping;

public class GroundShipping implements ShippingMethod {
    @Override
    public double calculateCost(double weight) {
        return 5 + (weight * 2);
    }

    @Override
    public String getName() {
        return "Ground Shipping";
    }
}
