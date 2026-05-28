package solid_ecommerce.src.com.example.solid.database;

import solid_ecommerce.src.com.example.solid.invoice.Invoice;

public interface Database {
    void save(Invoice invoice);
}


//I — Interface Segregation Principle (ISP)
//
//Clients should not be forced to depend on methods they do not use.
//
//Here, we use small, focused interfaces:
//Payment, ShippingMethod, and Database are independent.
//CheckoutService only depends on what it actually needs.
