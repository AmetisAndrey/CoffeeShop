package payment;

public class PaymentFacade {

    private final CardPaymentService cardService = new CardPaymentService();
    private final CashPaymentService cashService = new CashPaymentService();

    public boolean processPayment(PaymentMethod method, double amount) {
        switch (method) {
            case CARD:
                return cardService.charge(amount);
            case CASH:
                return cashService.acceptCash(amount);
            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }
    }
}