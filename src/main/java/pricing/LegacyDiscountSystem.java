package pricing;

public class LegacyDiscountSystem {

    public double getDiscountPercent(String promoCode) {
        if (promoCode == null || promoCode.isBlank()) {
            return 0.0;
        }
        if ("COFFEEHERE".equalsIgnoreCase(promoCode)) {
            return 20.0;
        }
        if ("VIP5".equalsIgnoreCase(promoCode)) {
            return 5.0;
        }
        return 0.0;
    }
}