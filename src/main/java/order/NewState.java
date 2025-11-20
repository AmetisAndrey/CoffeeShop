package order;


public class NewState extends AbstractOrderState {
    @Override
    public String getName() {
        return "NEW";
    }

    @Override
    public void pay(Order order) {
        order.setState(new PaidState());
    }
}