package menu;

public class ItemsMenu {
    private final int id;
    private final String name;
    private final BeverageFactory factory;

    public ItemsMenu(int id, String name, BeverageFactory factory) {
        this.id = id;
        this.name = name;
        this.factory = factory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BeverageFactory getFactory() {
        return factory;
    }
}
