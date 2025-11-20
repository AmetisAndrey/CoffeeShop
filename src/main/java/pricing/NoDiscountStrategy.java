package pricing;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice) {
        return basePrice;
    }
}
