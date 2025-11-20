package order.command;

import order.BeverageOrder;
import order.Order;

public class AddItemCommand implements Command {

    private final Order order;
    private final BeverageOrder item;

    public AddItemCommand(Order order, BeverageOrder item) {
        this.order = order;
        this.item = item;
    }

    @Override
    public void execute() {
        order.addItem(item);
    }

    @Override
    public void undo() {
        order.removeItem(item);
    }
}
