package order;

public interface OrderObserver {
    void onOrderStateChanged(Order order, OrderState newState);
}
