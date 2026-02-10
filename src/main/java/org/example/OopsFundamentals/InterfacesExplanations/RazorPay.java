package org.example.OopsFundamentals.InterfacesExplanations;

public class RazorPay implements PaymentGateway{
    @Override
    public void initiatePayment(double amount) {
        System.out.println("Razor Pay payment is happening." + amount) ;
    }
}
