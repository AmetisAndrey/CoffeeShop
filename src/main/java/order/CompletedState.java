package order;

public class CompletedState extends AbstractOrderState {
    @Override
    public String getName() {
        return "Заказ выполнен";
    }
}