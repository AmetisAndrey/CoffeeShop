package menu;

import beverage.Americano;
import beverage.Beverage;

public class AmericanoFactory implements BeverageFactory {
    @Override
    public Beverage create() {
        return new Americano();
    }
}
