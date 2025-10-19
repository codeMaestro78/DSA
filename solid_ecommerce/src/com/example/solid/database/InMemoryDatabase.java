package solid_ecommerce.src.com.example.solid.database;

import solid_ecommerce.src.com.example.solid.invoice.Invoice;

public class InMemoryDatabase implements Database {
    @Override
    public void save(Invoice invoice) {
        System.out.println("Invoice save to InMemory database");
    }
}
