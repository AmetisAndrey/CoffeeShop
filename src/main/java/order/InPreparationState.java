package order;


public class InPreparationState extends AbstractOrderState {
    @Override
    public String getName() {
        return "Заказ готовится";
    }

    @Override
    public void markReady(Order order) {
        order.setState(new ReadyState());
    }
}