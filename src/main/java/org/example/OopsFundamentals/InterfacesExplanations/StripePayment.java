package org.example.OopsFundamentals.InterfacesExplanations;

public class StripePayment implements PaymentGateway{
    @Override
    public void initiatePayment(double amount) {
        System.out.println("Stripe Payment is happening." + amount);
    }
}
