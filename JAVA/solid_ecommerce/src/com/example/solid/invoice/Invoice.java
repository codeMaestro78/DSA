package solid_ecommerce.src.com.example.solid.invoice;

import solid_ecommerce.src.com.example.solid.model.Cart;
import solid_ecommerce.src.com.example.solid.shipping.ShippingMethod;

public class Invoice {

    private Cart cart;
    private ShippingMethod shipping;
    private double total;

    public Invoice(Cart cart, ShippingMethod shipping, double total) {
        this.cart = cart;
        this.shipping = shipping;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public Cart getCart() {
        return cart;
    }

    public ShippingMethod getShipping() {
        return shipping;
    }
}


//D — Dependency Inversion Principle (DIP)
//
//High-level modules should not depend on low-level modules.
//Both should depend on abstractions.
//
//Our high-level CheckoutService depends on interfaces, not concrete classes
