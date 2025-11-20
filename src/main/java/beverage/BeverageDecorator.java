package beverage;

public abstract class BeverageDecorator implements Beverage {

    protected final Beverage delegate;

    protected BeverageDecorator(Beverage delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public double getCost() {
        return delegate.getCost();
    }
}