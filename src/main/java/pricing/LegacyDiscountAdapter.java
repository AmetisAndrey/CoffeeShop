package pricing;

public class LegacyDiscountAdapter implements DiscountStrategy {

    private final LegacyDiscountSystem legacy;
    private final String promoCode;

    public LegacyDiscountAdapter(LegacyDiscountSystem legacy, String promoCode) {
        this.legacy = legacy;
        this.promoCode = promoCode;
    }

    @Override
    public double applyDiscount(double basePrice) {
        double percent = legacy.getDiscountPercent(promoCode);
        return basePrice * (1.0 - percent / 100.0);
    }
}