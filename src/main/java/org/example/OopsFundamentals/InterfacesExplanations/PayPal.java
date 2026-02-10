package org.example.OopsFundamentals.InterfacesExplanations;

public class PayPal implements PaymentGateway{
    @Override
    public void initiatePayment(double amount) {
        System.out.println("Payment is happening through paypal" + amount);
    }
}
