package order;

import pricing.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id = UUID.randomUUID().toString();
    private final List<BeverageOrder> items = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();

    private OrderState state = new NewState();

    public String getId() {
        return id;
    }

    public List<BeverageOrder> getItems() {
        return items;
    }

    public void addItem(BeverageOrder order) {
        items.add(order);
    }

    public void removeItem(BeverageOrder order) {
        items.remove(order);
    }

    public OrderState getState() {
        return state;
    }

    void setState(OrderState state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (OrderObserver obs : observers) {
            obs.onOrderStateChanged(this, state);
        }
    }

    public void pay() {
        state.pay(this);
    }

    public void startPreparation() {
        state.startPreparation(this);
    }

    public void markReady() {
        state.markReady(this);
    }

    public void complete() {
        state.complete(this);
    }

    public double calculateTotal(PricingStrategy strategy) {
        return strategy.calculateTotal(this);
    }
    public boolean isCompleted() {
        return state instanceof CompletedState;
    }
}
