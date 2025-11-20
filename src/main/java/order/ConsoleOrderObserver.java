package order;

public class ConsoleOrderObserver implements OrderObserver {

    @Override
    public void onOrderStateChanged(Order order, OrderState newState) {

        String emoji;
        String state = newState.getName();

        switch (state) {
            case "NEW":
            case "Заказ получен":
                emoji = "🟣 Получен";
                break;

            case "PAID":
            case "Заказ оплачен":
                emoji = "💳 Оплачен";
                break;

            case "IN_PREPARATION":
            case "Заказ готовится":
                emoji = "🟡 Готовится";
                break;

            case "READY":
            case "Заказ готов":
                emoji = "🔵 Готов";
                break;

            case "COMPLETED":
            case "Заказ завершён":
                emoji = "🟢 Завершён";
                break;

            default:
                emoji = state;
        }

        String shortId = order.getId().substring(0, 5) + "...";

        System.out.println("🔔 Статус заказа №" + shortId + " → " + emoji);
    }
}