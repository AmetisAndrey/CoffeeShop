package beverage;

public class MilkDecorator extends BeverageDecorator {

    public MilkDecorator(Beverage delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return delegate.getName() + " + milk";
    }

    @Override
    public double getCost() {
        return delegate.getCost() + 25;
    }
}
