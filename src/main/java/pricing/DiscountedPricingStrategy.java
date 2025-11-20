package pricing;

import order.BeverageOrder;
import order.Order;

public class DiscountedPricingStrategy implements PricingStrategy {

    private final DiscountStrategy discountStrategy;

    public DiscountedPricingStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    @Override
    public double calculateTotal(Order order) {
        double base = 0;
        for (BeverageOrder item : order.getItems()) {
            base += item.getTotalCost();
        }
        return discountStrategy.applyDiscount(base);
    }
}