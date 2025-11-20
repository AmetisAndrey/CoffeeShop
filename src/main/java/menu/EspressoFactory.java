package menu;

import beverage.Beverage;
import beverage.Espresso;

public class EspressoFactory implements BeverageFactory {
    @Override
    public Beverage create() {
        return new Espresso();
    }
}
