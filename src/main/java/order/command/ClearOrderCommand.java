package order.command;

import order.BeverageOrder;
import order.Order;

import java.util.ArrayList;
import java.util.List;

public class ClearOrderCommand implements Command {

    private final Order order;
    private List<BeverageOrder> backup = new ArrayList<>();

    public ClearOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        backup = new ArrayList<>(order.getItems());
        order.getItems().clear();
    }

    @Override
    public void undo() {
        order.getItems().addAll(backup);
    }
}