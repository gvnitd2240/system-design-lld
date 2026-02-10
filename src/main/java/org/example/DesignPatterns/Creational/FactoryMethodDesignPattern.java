package org.example.DesignPatterns.Creational;

/**
 * https://algomaster.io/learn/lld/factory-method
 * The Factory Method Design Pattern is a creational pattern
 * that provides an interface for creating objects in a
 * superclass, but allows subclasses
 * to alter the type of objects that will be created.
 * */
public class FactoryMethodDesignPattern {

    /**
     * The Problem: Sending Notifications
     * */

    static class EmailNotification{
        public void send(){
            System.out.println("Sent.");
        }
    }

    static class NotificationService{
        public void send(EmailNotification notification){
            notification.send();
        }

        public void send(SMSNotification notification){
            notification.send();
        }

    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();
        NotificationService notificationService = new NotificationService();
        notificationService.send(emailNotification);
        notificationService.send(smsNotification);
    }

    /**
     * Now - SMS notifications.
     * */

    static class SMSNotification{
        public void send(){
            System.out.println("Sent.");
        }
    }

    /**
     * Every time you add a new notification channel,
     * you must modify the same core logic.
     * It violates key design principles, especially the
     * Open/Closed Principle—the idea that classes should be
     * open for
     * extension but closed for modification.
     * */

    /**
     * A Simple Factory
     * A Simple Factory is not a formal design pattern
     * in the Gang of Four (GoF) book,
     * but it’s one of the most practical and
     * widely-used refactoring
     * techniques in real-world codebases.
     *
     * Idea -
     * a separate class (a “factory”) whose only job
     * is to centralize
     * and encapsulate object creation
     *
     * The notification service no longer needs to know
     * which concrete class to instantiate.
     * It simply asks the
     * factory for the right type of notification.
     */

    public static class FactoryMethodDesignPatternImpl{
        static class SimpleNotificationFactory {
            public static Notification createNotification(String type) {
                if (type == "EMAIL") {
                    return new EmailNotification();
                } else if (type == "SMS") {
                    return new SMSNotification();
                }  else if (type == "PUSH_NOTIFICATION") {
                    return new PushNotification();
                }
                else {
                    throw new UnsupportedOperationException("Not supported.");
                }
            }
        }

        interface  Notification
        {
            public void send();
        }

        public static class EmailNotification implements Notification{

            @Override
            public void send() {
                System.out.println("Email Sent.");
            }
        }

        public static class SMSNotification implements Notification{

            @Override
            public void send() {
                System.out.println("SMS Sent.");
            }
        }

        public static class PushNotification implements Notification{

            @Override
            public void send() {
                System.out.println("Push Notification Sent.");
            }
        }

        public static class NotificationService{
            public void send(String type){
                Notification notification = SimpleNotificationFactory.createNotification(type);
                notification.send();

            }
        }

        /**
         * Remove conditional from createNotification functions.
         * */

        abstract static class NotificationCreator{
            public abstract Notification createNotification();

            public void send(){
                Notification notification = createNotification();
                notification.send();
            }
        }

        static class EmailNotificationCreator extends NotificationCreator{

            @Override
            public Notification createNotification() {
                return new EmailNotification();
            }
        }

        static class SMSNotificationCreator extends NotificationCreator{

            @Override
            public Notification createNotification() {
                return new SMSNotification();
            }
        }

        static class PushNotificationCreator extends NotificationCreator{
            @Override
            public Notification createNotification() {
                return new PushNotification();
            }
        }

        public static void main(String[] args) {
            NotificationService notificationService = new NotificationService();
            notificationService.send("EMAIL");

            NotificationCreator notificationCreator;

            notificationCreator = new EmailNotificationCreator();
            notificationCreator.send();

            notificationCreator = new SMSNotificationCreator();
            notificationCreator.send();

            notificationCreator = new PushNotificationCreator();
            notificationCreator.send();
        }
    }

    /**
     * Define the Product Interface - Notification
     * Define Concrete Products - SMS, EMAIL, PUSH NOTIFICATIONS
     * Define an Abstract Creator - We create an abstract class
     *      that declares the factory method createNotification(), and optionally includes shared behavior like send()that defines the high-level logic of sending a notification by using whatever
     *      object createNotification() provides.
     * Define Concrete Creators
     * Using it in the Client Code
     * */
}