package menu;

import beverage.Beverage;
import beverage.Latte;

public class LatteFactory implements BeverageFactory {
    @Override
    public Beverage create() {
        return new Latte();
    }
}
