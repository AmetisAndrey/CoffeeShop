package pricing;

import order.Order;

public interface PricingStrategy {
    double calculateTotal(Order order);
}
