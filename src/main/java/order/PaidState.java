package order;

public class PaidState extends AbstractOrderState {
    @Override
    public String getName() {
        return "Заказ получен";
    }

    @Override
    public void startPreparation(Order order) {
        order.setState(new InPreparationState());
    }
}