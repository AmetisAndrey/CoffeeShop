package beverage;

public class WhipCreamDecorator extends BeverageDecorator {

    public WhipCreamDecorator(Beverage delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return delegate.getName() + " + взбитые сливки";
    }

    @Override
    public double getCost() {
        return delegate.getCost() + 35;
    }
}