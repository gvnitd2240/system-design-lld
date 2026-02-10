package org.example.DesignPatterns.Miscellaneous;

import org.example.DesignPatterns.Creational.FactoryMethodDesignPattern;

/**
 * https://algomaster.io/learn/lld/dependency-injection
 * Dependency Injection is a design pattern where an object does not
 * create its dependencies itself.
 *
 * Instead, the dependencies are provided (injected) from the outside.
 *
 * 👉 “Don’t build your dependencies, receive them.”
 *
 * Dependency Injection is a pattern where dependencies are
 * provided from outside,
 * reducing coupling and improving testability and flexibility.
 * */
public class DependencyInjectionPattern {

    static class EmailService{};
    static class NotificationService{};

    static class OrderService {
        private EmailService emailService = new EmailService();
    }
    /**
     * ❌ Tight coupling
     * ❌ Hard to test
     * ❌ Cannot switch Email → SMS easily
     * */

    static class OrderService1 {
        private NotificationService notificationService;

        public OrderService1(NotificationService notificationService){
            this.notificationService = notificationService;
        }
    }
}
