package org.example.DesignPatterns.Behavior;

import org.example.DesignPatterns.Behavior.StateDesignPattern;

import java.io.ObjectInputFilter;

/**
 * https://algomaster.io/learn/lld/null-object
 * Imagine you're building a logging system. Different parts of
 * your application need to log events, but some deployments
 * need logging disabled entirely.
 * You start sprinkling null checks everywhere:
 *
 * The Null Object Pattern solves this by replacing null references with
 * objects that do nothing.
 * Instead of checking for null, you call methods that silently succeed.
 * */
public class NullObjectPattern {
    /**
     * 1. What is the Null Object Pattern?
     * The Null Object Pattern is a behavioral design pattern that uses an
     * object with default "do nothing" behavior instead of using null
     * references.
     * This eliminates the need for null checks throughout your code.
     *
     * The key insight is that "absence of behavior" is still a behavior.
     * By encapsulating it in an object,
     * you treat present and absent cases uniformly.
     *
     * It's a special case of the Strategy
     * pattern where one strategy is to "do nothing."
     * */

    /**
     * 2. The Problem: Null Checks Everywhere
     * Let's look at a notification system without the Null Object pattern:
     *
     * */

    public static class Order{
        public String customer;
        private String status;

        public Order(String customer, String status) {
            this.customer = customer;
            this.status = status;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCustomer() {
            return customer;
        }

        public String getStatus() {
            return status;
        }
    };

    interface Noitfier{
        void notify(String customer, String message);
    }
    public static class EmailNotifier implements Noitfier{
        @Override
        public void notify(String customer, String message) {
            System.out.println("Mail sent to" + customer + " With Message" + message);
        }
    };
    public static class SmsNotifier  implements Noitfier{
        @Override
        public void notify(String customer, String message) {
            System.out.println("Sms sent to" + customer + " With Message" + message);
        }
    };
    public static class PushNotifier implements Noitfier{
        @Override
        public void notify(String customer, String message) {
            System.out.println("PushNotification sent to" + customer + " With Message" + message);
        }
    };

    public static class NullNotifier implements Noitfier{
        @Override
        public void notify(String customer, String message) {}
    };

    static class NullObjectPatternNaive{
        public static class OrderService{
            private EmailNotifier emailNotifier;
            private SmsNotifier smsNotifier;
            private PushNotifier pushNotifier;

            public void processOrder(Order order) {
                order.setStatus("PROCESSED");

                if (emailNotifier != null) {
                    emailNotifier.notify(order.getCustomer(), "Order processed");
                }

                if (smsNotifier != null) {
                    smsNotifier.notify(order.getCustomer(), "Order processed");
                }
                if (pushNotifier != null) {
                    pushNotifier.notify(order.getCustomer(), "Order processed");
                }
            }
        }

        /**
         * Problem 1: Defensive Code Everywhere
         * Problem 2: Violates Open/Closed Principle
         * Problem 3: Scattered Logic
         * Problem 4: Harder to Test
         * Problem 5: Reduced Readability
         * */
    }


    public static class NullObjectPatternImpl{
        public static class OrderService{
            private Noitfier notifier;

            public OrderService(Noitfier notifier){
                this.notifier = notifier;
            }

            public void processOrder(Order order) {
                order.setStatus("PROCESSED");
                notifier.notify(order.getCustomer(), "Order Processed");
            }
        }
    }

    public static void main(String[] args) {
        Noitfier emailNotifier = new EmailNotifier();
        NullObjectPatternImpl.OrderService orderService = new NullObjectPatternImpl.OrderService(emailNotifier);
        Order order = new Order(
                "1222",
                "PROCESSED"
        );
        orderService.processOrder(order);

        Noitfier notifier = new NullNotifier();
        NullObjectPatternImpl.OrderService orderService1 = new NullObjectPatternImpl.OrderService(notifier);
        orderService1.processOrder(order);
    }
    /**
     * Common Use Case:
     *
     * 1. Logging Systems
     * 2. Optional Features
     * 3. Default Values in Collections
     * 4. Strategy Pattern with "No Strategy"
     * */

    /**Implementation Patterns */

    interface Logger{
        void log(String message);
    }

    public static class NullLogger implements Logger{
        private static final NullLogger INSTANCE = new NullLogger();

        private NullLogger() {}

        public static NullLogger getInstance() {
            return INSTANCE;
        }


        @Override
        public void log(String message) {
            // Do nothing.
        }
    }

    /**
    public static class LoggerFactory {
     public static Logger create(Config config) {
          if (config.isLoggingEnabled()) {
               return new FileLogger(config.getLogPath());
           }
         return NullLogger.getInstance();
        }
    }*/
}
