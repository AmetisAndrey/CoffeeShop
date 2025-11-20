package order;

public abstract class AbstractOrderState implements OrderState {

    protected void invalid(String action) {
        throw new IllegalStateException(
                "Cannot " + action + " when order is in state " + getName()
        );
    }

    @Override
    public void pay(Order order) {
        invalid("pay");
    }

    @Override
    public void startPreparation(Order order) {
        invalid("start preparation");
    }

    @Override
    public void markReady(Order order) {
        invalid("mark ready");
    }

    @Override
    public void complete(Order order) {
        invalid("complete");
    }
}