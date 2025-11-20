package ui;

import beverage.Beverage;
import beverage.MilkDecorator;
import beverage.SyrupDecorator;
import beverage.WhipCreamDecorator;
import Config.AppConfig;
import menu.ItemsMenu;
import menu.MenuRepository;
import order.BeverageOrder;
import order.ConsoleOrderObserver;
import order.Order;
import order.command.AddItemCommand;
import order.command.ClearOrderCommand;
import order.command.CommandManager;
import payment.PaymentFacade;
import payment.PaymentMethod;
import pricing.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("==================================");
        System.out.println("       Добро пожаловать в CoffeeShop!");
        System.out.println("==================================");

        Order order = new Order();
        order.addObserver(new ConsoleOrderObserver());

        CommandManager commandManager = new CommandManager();
        MenuRepository menu = MenuRepository.getInstance();
        PaymentFacade paymentFacade = new PaymentFacade();

        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1) Показать меню напитков");
            System.out.println("2) Добавить напиток");
            System.out.println("3) Отменить последнее действие");
            System.out.println("4) Очистить заказ");
            System.out.println("5) Показать заказ");
            System.out.println("6) Оформить заказ");
            System.out.println("0) Выход");
            System.out.print("Выберите пункт: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    printMenu(menu.getItems());
                    break;
                case 2:
                    BeverageOrder item = createBeverageOrder(menu);
                    if (item != null) {
                        commandManager.execute(new AddItemCommand(order, item));
                        System.out.println("Добавлено: " + item.getDescription());
                    }
                    break;
                case 3:
                    commandManager.undoLast();
                    System.out.println("Последнее действие отменено.");
                    break;
                case 4:
                    commandManager.execute(new ClearOrderCommand(order));
                    System.out.println("Заказ очищен.");
                    break;
                case 5:
                    printOrder(order);
                    break;
                case 6:
                    checkout(order, paymentFacade);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неизвестный пункт меню.");
            }
        }

        System.out.println("Спасибо за посещение CoffeeShop!");
    }

    private void printMenu(List<ItemsMenu> items) {
        System.out.println("\n=== МЕНЮ НАПИТКОВ ===");
        for (ItemsMenu item : items) {
            System.out.println(item.getId() + ") " + item.getName());
        }
    }

    private BeverageOrder createBeverageOrder(MenuRepository menu) {
        printMenu(menu.getItems());

        System.out.print("Введите ID напитка: ");
        int id = readInt();

        ItemsMenu menuItem = menu.findById(id);
        if (menuItem == null) {
            System.out.println("Неверный ID напитка.");
            return null;
        }

        Beverage beverage = menuItem.getFactory().create();

        System.out.print("Добавить молоко? (y/n): ");
        if (readYesNo()) beverage = new MilkDecorator(beverage);

        System.out.print("Добавить сироп? (y/n): ");
        if (readYesNo()) beverage = new SyrupDecorator(beverage);

        System.out.print("Добавить взбитые сливки? (y/n): ");
        if (readYesNo()) beverage = new WhipCreamDecorator(beverage);

        System.out.print("Количество: ");
        int qty = readInt();

        System.out.print("С собой? (y/n): ");
        boolean takeAway = readYesNo();

        System.out.print("Введите имя клиента: ");
        String name = scanner.nextLine();

        return new BeverageOrder.Builder()
                .beverage(beverage)
                .quantity(qty)
                .takeAway(takeAway)
                .customerName(name)
                .build();
    }

    private void printOrder(Order order) {
        System.out.println("\n=== ТЕКУЩИЙ ЗАКАЗ ===");
        if (order.getItems().isEmpty()) {
            System.out.println("Заказ пуст.");
            return;
        }

        for (BeverageOrder item : order.getItems()) {
            System.out.println("- " + item.getDescription() + " = " + item.getTotalCost());
        }
    }

    private void checkout(Order order, PaymentFacade paymentFacade) {
        if (order.getItems().isEmpty()) {
            System.out.println("Нечего оформлять — заказ пуст.");
            return;
        }

        if (order.isCompleted()) {
            System.out.println("Этот заказ уже завершен. Создайте новый заказ.");
            return;
        }

        // Промокод
        System.out.print("Промокод (или Enter): ");
        String promo = scanner.nextLine().trim();

        PricingStrategy strategy;

        if (promo.isEmpty()) {
            strategy = new BasicPricingStrategy();
        } else {
            DiscountStrategy discount =
                    new LegacyDiscountAdapter(new LegacyDiscountSystem(), promo);
            strategy = new DiscountedPricingStrategy(discount);
        }

        double total = order.calculateTotal(strategy);

        System.out.println("Итого к оплате: " + total + " " + AppConfig.getInstance().getCurrency());
        System.out.print("Оплатить картой? (y/n, n = наличные): ");
        boolean card = readYesNo();

        PaymentMethod method = card ? PaymentMethod.CARD : PaymentMethod.CASH;

        if (!paymentFacade.processPayment(method, total)) {
            System.out.println("Ошибка оплаты!");
            return;
        }

        order.pay();
        order.startPreparation();

        System.out.println("Оплата прошла успешно!");
        System.out.println("Заказ передан на приготовление. Ожидайте готовности!");
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Введите число: ");
            }
        }
    }

    private boolean readYesNo() {
        String line = scanner.nextLine().trim().toLowerCase();
        return line.startsWith("y") || line.startsWith("д");
    }
}
