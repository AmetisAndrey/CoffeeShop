package beverage;

public class SyrupDecorator extends BeverageDecorator {

    public SyrupDecorator(Beverage delegate) {
        super(delegate);
    }

    @Override
    public String getName() {
        return delegate.getName() + " + Сироп";
    }

    @Override
    public double getCost() {
        return delegate.getCost() + 20;
    }
}