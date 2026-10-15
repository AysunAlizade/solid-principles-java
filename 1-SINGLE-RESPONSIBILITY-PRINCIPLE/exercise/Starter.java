class Invoice {
    private String customerName;
    private double[] itemPrices;

    public Invoice(String customerName, double[] itemPrices) {
        this.customerName = customerName;
        this.itemPrices = itemPrices.clone();
    }

    public String getCustomerName() {
        return customerName;
    }

    public double[] getItemPrices() {
        return itemPrices.clone();
    }
}

// 1. Responsible only for calculating the total
class InvoiceCalculator {

    public double calculateTotal(Invoice invoice) {
        double total = 0;

        for (double price : invoice.getItemPrices()) {
            total += price;
        }

        return total;
    }
}

// 2. Responsible only for printing the invoice
class InvoicePrinter {
    private InvoiceCalculator calculator;

    public InvoicePrinter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public void printInvoice(Invoice invoice) {
        System.out.println("Invoice for: " + invoice.getCustomerName());

        for (double price : invoice.getItemPrices()) {
            System.out.println(" - $" + price);
        }

        System.out.printf("Total: $%.2f%n",
                calculator.calculateTotal(invoice));
    }
}

// 3. Responsible only for simulating invoice storage
class InvoiceStorage {

    public void saveToFile(Invoice invoice, String filename) {
        System.out.println("Saving invoice to " + filename);
    }
}

// 4. Responsible only for simulating email delivery
class InvoiceMailer {

    public void emailInvoice(Invoice invoice, String emailAddress) {
        System.out.println("Emailing invoice to " + emailAddress);
    }
}

// The class where the program starts
public class Starter {

    public static void main(String[] args) {
        Invoice invoice = new Invoice(
                "Jane Doe",
                new double[]{19.99, 42.50, 5.00}
        );

        InvoiceCalculator calculator = new InvoiceCalculator();

        // Calculation is independent of other operations
        double total = calculator.calculateTotal(invoice);
        System.out.printf("Calculated total: $%.2f%n", total);

        if (Math.abs(total - 67.49) < 0.000001) {
            System.out.println("Total check passed!");
        }

        InvoicePrinter printer = new InvoicePrinter(calculator);
        printer.printInvoice(invoice);

        InvoiceStorage storage = new InvoiceStorage();
        storage.saveToFile(invoice, "invoice-001.txt");

        InvoiceMailer mailer = new InvoiceMailer();
        mailer.emailInvoice(invoice, "jane@example.com");
    }
}
