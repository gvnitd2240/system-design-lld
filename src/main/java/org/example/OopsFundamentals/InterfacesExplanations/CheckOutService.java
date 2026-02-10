package org.example.OopsFundamentals.InterfacesExplanations;

public class CheckOutService {
    private PaymentGateway paymentGateway;

    CheckOutService(){

    }

    CheckOutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void setPaymentGateway(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(double amount){
        paymentGateway.initiatePayment(amount);
    }

    public void print(){
        System.out.println("fuck you.");
    }
}
