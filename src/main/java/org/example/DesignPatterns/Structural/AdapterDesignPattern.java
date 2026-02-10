package org.example.DesignPatterns.Structural;


/**
 * https://algomaster.io/learn/lld/adapter
 * The Adapter Design Pattern is a structural
 * design pattern that allows incompatible
 * interfaces to work together by converting the interface
 * of one class into another that the client expects.
 *
 * Example:
 * 1. legacy system or a third-party library
 * 2. reuse existing functionality
 * 3. bridge the gap between new and old code
 * How to identify?
 * 1. incompatible interfaces
 * ---
 *  tightly coupled, and violates the Open/Closed Principle
 * ---
 * Wrapper class that sits between your system and the incompatible
 * component. Translates call from your interface into calls the legacy/third
 * party systems without changing either side.
 * */
public class AdapterDesignPattern {

    /**
     * 1. The Problem: Incompatible Payment Interfaces
     * Imagine you’re building the checkout component
     * of an e-commerce application.
     * ----
     * Your Checkout Service is designed to work
     * with a Payment Interface for handling payments.
     * */

    public static class AdapterDesignNaiveApproach {
        interface PaymentProcessor{
            void processPayment (double amount, String currency);
            boolean isPaymentSuccessful();
            String getTransactionId();
        }


        static class InHousePaymentProcessor implements PaymentProcessor{
            private String transactionId;
            private boolean isPaymentSuccessful;

            @Override
            public void processPayment(double amount, String currency) {
                System.out.println("InHousePaymentProcessor: Processing payment of " + amount + " " + currency);
                transactionId = "TXN_" + System.currentTimeMillis();
                isPaymentSuccessful = true;
                System.out.println("InHousePaymentProcessor: Payment successful. Txn ID: " + transactionId);
            }

            @Override
            public boolean isPaymentSuccessful() {
                return isPaymentSuccessful;
            }

            @Override
            public String getTransactionId() {
                return transactionId;
            }
        }

        public static class CheckOutService{
            private PaymentProcessor paymentProcessor;

            CheckOutService(PaymentProcessor paymentProcessor){
                this.paymentProcessor = paymentProcessor;
            }

            void checkout(double amount, String currency){
                paymentProcessor.processPayment(amount, currency);

                if(paymentProcessor.isPaymentSuccessful()){
                    System.out.println("Success.");
                } else{
                    System.out.println("CheckoutService: Order failed. Payment was not successful.");
                }
            }
        }

        public static void main(String[] args) {
            PaymentProcessor processor = new InHousePaymentProcessor();
            CheckOutService checkout = new CheckOutService(processor);
            checkout.checkout(199.99, "USD");
        }
    }

    /**
     * integrate with a legacy third-party payment provider
     * but with a completely different interface.
     * */


    static class LegacyGateway {
        private long transactionReference;
        private boolean isPaymentSuccessful;

        public void executeTransaction(double totalAmount, String currency) {
            System.out.println("LegacyGateway: Executing transaction for "
                    + currency + " " + totalAmount);
            transactionReference = System.nanoTime();
            isPaymentSuccessful = true;
            System.out.println("LegacyGateway: Transaction executed successfully. Txn ID: "
                    + transactionReference);
        }

        public boolean checkStatus(long transactionReference) {
            System.out.println("LegacyGateway: Checking status for ref: " + transactionReference);
            return isPaymentSuccessful;
        }

        public long getReferenceNumber() {
            return transactionReference;
        }
    }

    /**
     *  two incompatible interfaces -> PaymentProcessor, LegacyGateway
     *  CheckoutService -> PaymentProcessor
     *
     *  Challenge:
     *  Can't change the CheckoutService.
     *  Can't modify LegacyGateway.
     *  But you must make them work together.
     *
     *  Now what you need to do?
     *  Translator which will take Payment Processor and convert it to Legacy Gateway.
     * */

    /**
     * The Adapter acts as a bridge between an incompatible
     * interface and what the client actually expects.
     *
     * Two Types of Adapters
     * 1. Object Adapter (Preferred in Java)
     *   Uses composition: the adapter holds a reference to the adaptee (the object it wraps).
     *   Allows flexibility and reuse across class hierarchies.
     *   This is the most common and recommended approach in Java.
     * 2. Class Adapter (Rare in Java)
     *    Uses inheritance: the adapter inherits from both the target interface and the adaptee.
     *    Requires multiple inheritance, which Java doesn’t support for classes.
     *    More suitable for languages like C++.
     * */

    /**
     * Target Interface -> PaymentProcessor -> The interface that the client code expects and uses.
     * Adaptee -> LegacyGateway -> The existing class with an incompatible interface that needs adapting.
     * Adapter -> The class that implements the Target interface and uses the Adaptee internally.
     * Client -> CheckoutService
     * */


    public static class AdapterDesignPatternImpl{
        interface PaymentProcessor{
            void processPayment (double amount, String currency);
            boolean isPaymentSuccessful();
            String getTransactionId();
        }


        static class InHousePaymentProcessor implements PaymentProcessor {
            private String transactionId;
            private boolean isPaymentSuccessful;

            @Override
            public void processPayment(double amount, String currency) {

            }

            @Override
            public boolean isPaymentSuccessful() {
                return false;
            }

            @Override
            public String getTransactionId() {
                return null;
            }
        }

        public static class CheckOutService{
            private PaymentProcessor paymentProcessor;

            CheckOutService(PaymentProcessor paymentProcessor){
                this.paymentProcessor = paymentProcessor;
            }

            void checkout(double amount, String currency){
                paymentProcessor.processPayment(amount, currency);

                if(paymentProcessor.isPaymentSuccessful()){
                    System.out.println("Success.");
                } else{
                    System.out.println("CheckoutService: Order failed. Payment was not successful.");
                }
            }
        }

        static class LegacyGateway {
            private long transactionReference;
            private boolean isPaymentSuccessful;

            public void executeTransaction(double totalAmount, String currency) {
                System.out.println("LegacyGateway: Executing transaction for "
                        + currency + " " + totalAmount);
                transactionReference = System.nanoTime();
                isPaymentSuccessful = true;
                System.out.println("LegacyGateway: Transaction executed successfully. Txn ID: "
                        + transactionReference);
            }

            public boolean checkStatus(long transactionReference) {
                System.out.println("LegacyGateway: Checking status for ref: " + transactionReference);
                return isPaymentSuccessful;
            }

            public long getReferenceNumber() {
                return transactionReference;
            }
        }

        static class LegacySystemAdapter implements PaymentProcessor{
            private final LegacyGateway legacyGateway; // Composition > Loosely coupled
            private long currentRef;


            LegacySystemAdapter(LegacyGateway legacyGateway){
                this.legacyGateway = legacyGateway;
            }

            @Override
            public void processPayment(double amount, String currency) {
                System.out.println("Adapter: Translating processPayment() for " + amount + " " + currency);
                legacyGateway.executeTransaction(amount, currency);
                currentRef = legacyGateway.getReferenceNumber();
            }

            @Override
            public boolean isPaymentSuccessful() {
                return legacyGateway.checkStatus(currentRef);
            }

            @Override
            public String getTransactionId() {
                return "LEGACY_TXN_" + currentRef;
            }
        }

        public static class ECommerceAppV2{
            public static void main(String[] args) {
                PaymentProcessor processor = new InHousePaymentProcessor();
                CheckOutService checkOutService = new CheckOutService(processor);
                checkOutService.checkout(100.00, "INR");


                LegacyGateway legacyGateway = new LegacyGateway();
                PaymentProcessor processor1 = new LegacySystemAdapter(legacyGateway);
                CheckOutService checkOutService1 = new CheckOutService(processor1);
                checkOutService1.checkout(100.00, "INR");
            }
        }



    }


}
