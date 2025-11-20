package menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static final MenuRepository INSTANCE = new MenuRepository();

    private final List<ItemsMenu> items = new ArrayList<>();

    private MenuRepository() {
        items.add(new ItemsMenu(1, "Экспрессо", new EspressoFactory()));
        items.add(new ItemsMenu(2, "Латте", new LatteFactory()));
        items.add(new ItemsMenu(3, "Американо", new AmericanoFactory()));
    }

    public static MenuRepository getInstance() {
        return INSTANCE;
    }

    public List<ItemsMenu> getItems() {
        return items;
    }

    public ItemsMenu findById(int id) {
        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
