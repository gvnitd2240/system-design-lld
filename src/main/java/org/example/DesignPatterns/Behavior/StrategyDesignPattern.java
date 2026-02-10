package org.example.DesignPatterns.Behavior;

/**
 * https://algomaster.io/learn/lld/strategy
 * The Strategy Design Pattern is a behavioral pattern that
 * lets you define a family of algorithms, encapsulate each one in its own class,
 * and make them interchangeable at runtime.
 *
 * At its core, the Strategy pattern is about
 * separating "what varies" from "what stays the same."
 *
 * 1. multiple ways to perform the same operation & choice might change at runtime
 * 2. avoid bloated conditional statements that select between different behaviors
 * 3. need to isolate algorithm-specific data and logic from the code that uses it
 * 4. Different clients might need different algorithms for the same task
 *
 *
 * */
public class StrategyDesignPattern {
    /**
     * 1. The Problem: Shipping Cost Calculation
     * building an e-commerce platform.
     * One of the features you need is a shipping cost calculator.
     *
     * Flat Rate: A fixed fee regardless of weight or distance
     * Weight-Based: Cost increases with package weight
     * Distance-Based: Different rates for different delivery zones
     * Express Delivery: Premium pricing for faster service
     * Third-Party API: Dynamic rates from carriers like FedEx or UPS
     * */

    static class Order {
        int weight;
        int distance;
        boolean expressDelivery;

        String destinationZone;

        public Order(int weight, int distance, boolean expressDelivery, String destinationZone) {
            this.weight = weight;
            this.distance = distance;
            this.expressDelivery = expressDelivery;
            this.destinationZone = destinationZone;
        }

        public int getWeight() {
            return weight;
        }

        public int getDistance() {
            return distance;
        }

        public boolean isExpressDelivery() {
            return expressDelivery;
        }

        public String getDestinationZone() {
            return destinationZone;
        }
    }

    static class StrategyDesignPatternNaive {

        public double calculateShippingCost(Order order, String strategyType) {
            double cost;

            switch (strategyType) {
                case "FLAT_RATE":
                    System.out.println("Calculating with Flat Rate strategy.");
                    cost = 10.0;
                    break;

                case "WEIGHT_BASED":
                    System.out.println("Calculating with Weight-Based strategy.");
                    cost = order.getWeight() * 2.5;
                    break;

                case "DISTANCE_BASED":
                    System.out.println("Calculating with Distance-Based strategy.");
                    if ("ZoneA".equals(order.getDestinationZone())) {
                        cost = 5.0;
                    } else if ("ZoneB".equals(order.getDestinationZone())) {
                        cost = 12.0;
                    } else {
                        cost = 20.0;
                    }
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown shipping strategy: " + strategyType
                    );
            }

            System.out.println("Calculated Shipping Cost: $" + cost);
            return cost;
        }

        public class ECommerceAppV1{
            public static void main(String[] args) {
                StrategyDesignPatternNaive calculator = new StrategyDesignPatternNaive();
                Order o1 = new Order(
                        10,
                        12,
                        true,
                        "ZoneA"
                );
                double cost = calculator.calculateShippingCost(o1, "DISTANCE_BASED");
                System.out.println(cost);
            }
        }

        /**
         * What's Wrong with This Approach?
         * 1. Violates the Open/Closed Principle
         * 2. Bloated Conditional Logic
         * 3. Difficult to Test in Isolation
         * 4. Risk of Code Duplication
         * 5. Low Cohesion -  every possible algorithm for shipping cost, rather than focusing on orchestrating the calculation.
         * */

    }

    /**
     * What We Really Need?
     * Each shipping algorithm lives in its own class
     * Adding a new algorithm does not require modifying existing classes
     * The calculator does not need to know which algorithm it is using
     * Algorithms can be swapped at runtime based on user preferences or business rules
     * Each algorithm can be tested independently
     * */


    /**
     * Strategy Pattern: The Strategy Pattern defines a family of
     * algorithms, encapsulates each one, and makes them interchangeable.
     * Strategy lets the algorithm vary independently from clients that use it.
     *
     * 1. Strategy Interface (e.g., ShippingStrategy)
     * 2. Concrete Strategies (e.g., FlatRateShipping, WeightBasedShipping)
     * 3. Context Class (e.g., ShippingCostService)
     *
     *
     * How It Works?
     * 1. The client creates a concrete strategy object
     * 2. The client passes the strategy to the context
     * 3. The context stores a reference to the strategy
     * 4. When the context needs to run the algorithm, it delegates to the strategy
     * 5. The client can swap the strategy at any time
     * */

    static class StrategyDesignPatternImpl {
        interface ShippingStrategy {
            public double cost(Order order);
        }

        static class FlatRateShipping implements ShippingStrategy {
            private double rate;

            public FlatRateShipping(double rate) {
                this.rate = rate;
            }

            @Override
            public double cost(Order order) {
                return rate;
            }
        }

        static class WeightBasedShipping implements ShippingStrategy {
            private final double ratePerKg;

            WeightBasedShipping(double ratePerKg) {
                this.ratePerKg = ratePerKg;
            }

            @Override
            public double cost(Order order) {
                return order.getWeight() * ratePerKg;
            }
        }

        static class DistanceBasedStrategy implements ShippingStrategy {
            private double ratePerKm;

            double cost = 0.0;

            DistanceBasedStrategy(double ratePerKm) {
                this.ratePerKm = ratePerKm;
            }

            @Override
            public double cost(Order order) {
                System.out.println("Calculating with Distance-Based strategy.");
                return switch (order.getDestinationZone()) {
                    case "ZoneA" -> ratePerKm * 5.0;
                    case "ZoneB" -> ratePerKm * 7.0;
                    default -> ratePerKm * 10.0;
                };
            }

        }

        static class ShippingCostService {
            private ShippingStrategy shippingStrategy;

            public ShippingCostService(ShippingStrategy shippingStrategy) {
                this.shippingStrategy = shippingStrategy;
            }

            public void setShippingStrategy(ShippingStrategy shippingStrategy) {
                this.shippingStrategy = shippingStrategy;
            }

            public double calculateShippingCost(Order order) {
                return shippingStrategy.cost(order);
            }
        }


        public static  class ECommerceAppV2{
            public static void main(String[] args) {
                ShippingStrategy shippingStrategy = new FlatRateShipping(10.0);
                ShippingCostService shippingCostService = new ShippingCostService(shippingStrategy);
                Order o1 = new Order(
                        10,
                        12,
                        true,
                        "ZoneA"
                );
                System.out.println(shippingCostService.calculateShippingCost(o1));

            }
        }

    }

    /**
     * 4. What We Gained?
         * Open/Closed Principle
         * Single Responsibility
         * Testability
         * Runtime flexibility
         * No string-based dispatch
         * Composition over inheritance
     * */

}
