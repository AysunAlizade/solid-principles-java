interface DiscountPolicy {
    double apply(double amount);
}

// Regular customers receive no discount
class RegularCustomerDiscount implements DiscountPolicy {

    @Override
    public double apply(double amount) {
        return 0.0;
    }
}

// Premium customers receive a 10% discount
class PremiumCustomerDiscount implements DiscountPolicy {

    @Override
    public double apply(double amount) {
        return amount * 0.10;
    }
}

// VIP customers receive a 20% discount
class VipCustomerDiscount implements DiscountPolicy {

    @Override
    public double apply(double amount) {
        return amount * 0.20;
    }
}

// Student customers receive a 15% discount
class StudentCustomerDiscount implements DiscountPolicy {

    @Override
    public double apply(double amount) {
        return amount * 0.15;
    }
}

// Applies a discount policy to an order
class PricingService {
    private DiscountPolicy discountPolicy;

    public PricingService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public double calculateFinalPrice(double amount) {
        return amount - discountPolicy.apply(amount);
    }
}

// Main class
public class Starter {

    public static void main(String[] args) {
        double amount = 200.0;

        DiscountPolicy regularPolicy = new RegularCustomerDiscount();

        DiscountPolicy premiumPolicy = new PremiumCustomerDiscount();

        DiscountPolicy vipPolicy = new VipCustomerDiscount();

        DiscountPolicy studentPolicy = new StudentCustomerDiscount();

        PricingService regularPricing = new PricingService(regularPolicy);

        PricingService premiumPricing = new PricingService(premiumPolicy);

        PricingService vipPricing = new PricingService(vipPolicy);

        PricingService studentPricing = new PricingService(studentPolicy);

        System.out.printf("Regular pays: $%.2f%n",
                regularPricing.calculateFinalPrice(amount));

        System.out.printf("Premium pays: $%.2f%n",
                premiumPricing.calculateFinalPrice(amount));

        System.out.printf("VIP pays: $%.2f%n",
                vipPricing.calculateFinalPrice(amount));

        System.out.printf("Student pays: $%.2f%n",
                studentPricing.calculateFinalPrice(amount));
    }
}
