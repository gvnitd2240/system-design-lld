package org.example.OopsFundamentals.InterfacesExplanations;

public class Main {
    public static void main(String[] args) {
        PaymentGateway stripePayment = new StripePayment();
        //constructor injection.
        CheckOutService service = new CheckOutService(stripePayment);
        service.checkout(120.05);

        PaymentGateway razorPay = new RazorPay();
        CheckOutService service1 = new CheckOutService();
        service1.setPaymentGateway(razorPay);
        service1.checkout(110.05);

        PaymentGateway paypal = new PayPal();
        CheckOutService service2 = new CheckOutService();
        service2.setPaymentGateway(paypal);
        service2.checkout(130.05);

    }
}
