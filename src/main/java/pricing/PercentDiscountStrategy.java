package pricing;

public class PercentDiscountStrategy implements DiscountStrategy {

    private final double percent;

    public PercentDiscountStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice * (1.0 - percent / 100.0);
    }
}