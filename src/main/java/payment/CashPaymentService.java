package payment;

public class CashPaymentService {
    public boolean acceptCash(double amount) {
        System.out.println("Оплата наличными: " + amount);
        return true;
    }
}