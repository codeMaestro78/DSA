package solid_ecommerce.src.com.example.solid.shipping;

public class ExpressShipping implements ShippingMethod {
    @Override
    public double calculateCost(double weight) {
        return 10 + (weight * 4);
    }

    @Override
    public String getName() {
        return "Express Shipping";
    }
}


//Both classes can be used interchangeably as ShippingMethod.
//That’s LSP — the system doesn’t care which subclass is used.
