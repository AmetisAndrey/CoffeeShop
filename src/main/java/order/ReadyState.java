package order;

public class ReadyState extends AbstractOrderState {
    @Override
    public String getName() {
        return "Заказ готов";
    }

    @Override
    public void complete(Order order) {
        order.setState(new CompletedState());
    }
}