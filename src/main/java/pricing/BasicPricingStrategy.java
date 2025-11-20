package pricing;

import order.BeverageOrder;
import order.Order;

public class BasicPricingStrategy implements PricingStrategy {
    @Override
    public double calculateTotal(Order order) {
        double sum = 0;
        for (BeverageOrder item : order.getItems()) {
            sum += item.getTotalCost();
        }
        return sum;
    }
}