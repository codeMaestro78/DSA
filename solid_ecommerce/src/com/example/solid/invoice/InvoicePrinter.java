package solid_ecommerce.src.com.example.solid.invoice;

public class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("==== Invoice ====");
        invoice.getCart().getItems().forEach(item -> System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + "=R" + item.getTotalPrice()));
        System.out.println("Shipping: " + invoice.getShipping().getName());
        System.out.println("Total Amount: " + invoice.getTotal());
        System.out.println("=================");
    }
}
