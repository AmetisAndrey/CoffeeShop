package payment;

public class CardPaymentService {
    public boolean charge(double amount) {
        System.out.println("Оплата картой: " + amount);
        return true;
    }
}
