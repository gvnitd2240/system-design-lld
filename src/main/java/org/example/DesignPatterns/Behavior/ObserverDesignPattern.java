package org.example.DesignPatterns.Behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * https://algomaster.io/learn/lld/observer
 * The Observer Design Pattern is a behavioral pattern
 * that defines a one-to-many dependency between objects so
 * that when one object (the subject) changes its state,
 * all its dependents (observers)
 * are automatically notified and updated.
 *
 * 1. You have multiple parts of the system that need to react to a change in one central component.
 * 2. You want to decouple the publisher of data from the subscribers who react to it.
 * 3. You need a dynamic, event-driven communication model without hardcoding who is listening to whom.
 *
 * Product which is out of stock for now. Once the product comes into the stock,
 * we gonna notify all the users.
 *
 * But as the number of subscribers grows, this approach becomes rigid,
 * hard to scale,
 * and violates the Open/Closed Principle.
 * */
public class ObserverDesignPattern {
    /**
     * 1. The Problem: Broadcasting Fitness Data
     * Imagine you are building a Fitness Tracker App that
     * connects to a wearable device.
     * The device continuously streams real-time fitness data: steps taken,
     * active minutes,
     * and calories burned. This data flows into a central FitnessData object.
     *
     * 1. Wearable Device
     * 2. FitnessData
     * 3. LiveActivityDisplay - Shows real-time stats on the dashboard
     * 4. ProgressLogger - Persists data to a database for trend analysis
     * 5. GoalNotifier - Sends alerts when the user hits milestones
     * */

    static class ObserverDesignPatternNaive{
        static class LiveActivityDisplayNaive{
            public void showStats(int steps, int activeMinutes, int calories) {
            }
        };
        static class ProgressLoggerNaive{
            public void logDataPoint(int steps, int activeMinutes, int calories) {
            }
        };
        static class NotificationServiceNaive{
            public void checkAndNotify(int steps) {
            }

            public void resetDailyNotifications() {
            }
        };

        static class FitnessDataNaive {
            private int steps;
            private int activeMinutes;
            private int calories;

            private LiveActivityDisplayNaive liveDisplay;
            private ProgressLoggerNaive progressLogger;
            private NotificationServiceNaive notificationService;

            public FitnessDataNaive(LiveActivityDisplayNaive liveDisplay, ProgressLoggerNaive progressLogger, NotificationServiceNaive notificationService) {
                this.liveDisplay = liveDisplay;
                this.progressLogger = progressLogger;
                this.notificationService = notificationService;
            }

            public void newFitnessDataPushed(int newSteps, int newActiveMinutes, int newCalories) {
                this.steps = newSteps;
                this.activeMinutes = newActiveMinutes;
                this.calories = newCalories;

                System.out.println("\nFitnessDataNaive: New data received - Steps: " + steps +
                        ", ActiveMins: " + activeMinutes + ", Calories: " + calories);

                liveDisplay.showStats(steps, activeMinutes, calories);
                progressLogger.logDataPoint(steps, activeMinutes, calories);
                notificationService.checkAndNotify(steps);
            }

            public void dailyReset(){
                //Reset Logic.

                if(notificationService != null){
                    notificationService.resetDailyNotifications();
                }
                System.out.println("FitnessDataNaive: Daily data reset.");
                newFitnessDataPushed(0, 0, 0);
            }
        }

        public static class FitnessAppNaiveClient {
            public static void main(String[] args) {
                LiveActivityDisplayNaive display = new LiveActivityDisplayNaive();
                ProgressLoggerNaive logger = new ProgressLoggerNaive();
                NotificationServiceNaive notifier = new NotificationServiceNaive();

                FitnessDataNaive fitnessData = new FitnessDataNaive(display, logger, notifier);

                fitnessData.newFitnessDataPushed(500, 5, 20);
                fitnessData.newFitnessDataPushed(9800, 85, 350);
                fitnessData.newFitnessDataPushed(10100, 90, 380); // Goal should be hit
                fitnessData.dailyReset();
            }
        }

        /** Problems with This Approach?
         * 1. Tight Coupling
         * 2. Violates the Open/Closed Principle
         * 3. Inflexible and Static Design - What if the user disables notifications in their settings?
         * 4. Responsibility Bloat
         * 5. Scalability Bottlenecks
         * */
    }

    /**
     * What We Really Need?
     * FitnessData to broadcast
     * changes to multiple listeners, without knowing who they are.
     * Each module to subscribe or unsubscribe dynamically
     * Loose coupling between the subject and observers
     * Each module to decide for itself how to respond to changes
     * */

    /**
     * Observer Pattern:
     * The Observer Design Pattern provides a clean and flexible solution
     * to the problem of broadcasting changes from one central object (the Subject)
     * to many dependent objects (the Observers) — all while keeping them loosely coupled.
     *
     * 1. Observer Interface (e.g., FitnessDataObserver) - Declares an update() method.
     * 2. Subject Interface (e.g., FitnessDataSubject) - Declares methods to:
     *      registerObserver() – subscribe to updates
     *      removeObserver() – unsubscribe from updates
     *      notifyObservers() – notify all current observers of a change
     *
     * 3. ConcreteSubject (e.g., FitnessData) -> FitnessDataSubject
     * 4. ConcreteObservers (e.g., LiveActivityDisplay)
     * */

    static class ObserverDesignPatternImpl{
        interface FitnessDataObserver{
            public void update(FitnessData data);
        }

        interface  FitnessDataSubject{
            public void registerObserver(FitnessDataObserver observer);
            public void removeObserver(FitnessDataObserver observer);
            public void notifyObservers();
        }

        static class FitnessData implements FitnessDataSubject{
            private int steps;
            private int activeMinutes;
            private int calories;

            List<FitnessDataObserver> observers = new ArrayList<>();

            @Override
            public void registerObserver(FitnessDataObserver observer) {
                observers.add(observer);
            }

            @Override
            public void removeObserver(FitnessDataObserver observer) {
                observers.remove(observer);
            }

            @Override
            public void notifyObservers() {
                for(FitnessDataObserver observer: observers){
                    observer.update(this);
                }
            }

            public void newFitnessDataPushed(int steps, int activeMinutes, int calories) {
                this.steps = steps;
                this.activeMinutes = activeMinutes;
                this.calories = calories;

                System.out.println("\nFitnessData: New data received – Steps: " + steps +
                        ", Active Minutes: " + activeMinutes + ", Calories: " + calories);

                notifyObservers();
            }

            public void dailyReset() {
                this.steps = 0;
                this.activeMinutes = 0;
                this.calories = 0;

                System.out.println("\nFitnessData: Daily reset performed.");
                notifyObservers();
            }
            // Getters
            public int getSteps() { return steps; }
            public int getActiveMinutes() { return activeMinutes; }
            public int getCalories() { return calories; }
        }

        static class LiveActivityDisplay implements FitnessDataObserver{
            @Override
            public void update(FitnessData data) {
                System.out.println("Live Display → Steps: " + data.getSteps() +
                        " | Active Minutes: " + data.getActiveMinutes() +
                        " | Calories: " + data.getCalories());

            }
        }
        static  class ProgressLogger implements FitnessDataObserver {
            @Override
            public void update(FitnessData data) {
                System.out.println("Logger → Saving to DB: Steps=" + data.getSteps() +
                        ", ActiveMinutes=" + data.getActiveMinutes() +
                        ", Calories=" + data.getCalories());
            }
        }
        static class GoalNotifier implements FitnessDataObserver {
            private final int stepGoal = 10000;
            private boolean goalReached = false;

            @Override
            public void update(FitnessData data) {
                if (data.getSteps() >= stepGoal && !goalReached) {
                    System.out.println("Notifier → 🎉 Goal Reached! You've hit " + stepGoal + " steps!");
                    goalReached = true;
                }
            }

            public void reset() {
                goalReached = false;
            }
        }

        public static class FitnessAppObserverDemo {
            public static void execute() {
                FitnessData fitnessData = new FitnessData();
                LiveActivityDisplay display = new LiveActivityDisplay();
                ProgressLogger logger = new ProgressLogger();
                GoalNotifier notifier = new GoalNotifier();


                // Register observers
                fitnessData.registerObserver(display);
                fitnessData.registerObserver(logger);
                fitnessData.registerObserver(notifier);

                fitnessData.newFitnessDataPushed(500, 5, 20);
                fitnessData.newFitnessDataPushed(9800, 85, 350);
                fitnessData.newFitnessDataPushed(10100, 90, 380); // Goal should trigger

                // Daily reset
                notifier.reset();
                fitnessData.dailyReset();
            }
        }

        /** What We Achieved?
         * Loose Coupling
         * Extensibility: Adding a new module (like WeeklySummaryGenerator) only requires implementing FitnessDataObserver — no changes to FitnessData
         * Runtime Flexibility: Observers can be added/removed dynamically (e.g., based on user settings)
         * Clean Separation of Concerns: Each module is responsible for its own behavior and logic
         * */
    }

    public static void main(String[] args) {
        ObserverDesignPatternImpl.FitnessAppObserverDemo.execute();
    }

    /**
     * Subject -> Internal State Change -> Notify the observers.
     * */
}
