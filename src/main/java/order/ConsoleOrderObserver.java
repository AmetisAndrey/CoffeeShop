package order;

public class ConsoleOrderObserver implements OrderObserver {

    @Override
    public void onOrderStateChanged(Order order, OrderState newState) {
        System.out.println("[NOTIFY] Order " + order.getId() +
                " is now in state: " + newState.getName());
    }
}