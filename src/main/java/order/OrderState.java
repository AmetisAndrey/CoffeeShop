package order;

public interface OrderState {
    String getName();
    void pay(Order order);
    void startPreparation(Order order);
    void markReady(Order order);
    void complete(Order order);
}