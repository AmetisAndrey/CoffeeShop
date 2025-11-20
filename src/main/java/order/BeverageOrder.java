package order;

import beverage.Beverage;

public class BeverageOrder {

    private final Beverage beverage;
    private final int quantity;
    private final boolean takeAway;
    private final String customerName;

    private BeverageOrder(Builder builder) {
        this.beverage = builder.beverage;
        this.quantity = builder.quantity;
        this.takeAway = builder.takeAway;
        this.customerName = builder.customerName;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalCost() {
        return beverage.getCost() * quantity;
    }

    public String getDescription() {
        return quantity + "x " + beverage.getName() + (takeAway ? " (take away)" : " (here)");
    }

    public static class Builder {
        private Beverage beverage;
        private int quantity = 1;
        private boolean takeAway;
        private String customerName;

        public Builder beverage(Beverage beverage) {
            this.beverage = beverage;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder takeAway(boolean takeAway) {
            this.takeAway = takeAway;
            return this;
        }

        public Builder customerName(String name) {
            this.customerName = name;
            return this;
        }

        public BeverageOrder build() {
            if (beverage == null) {
                throw new IllegalStateException("Beverage cannot be null");
            }
            return new BeverageOrder(this);
        }
    }
}
