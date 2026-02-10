package org.example.DesignPatterns.Behavior;

/**
 * https://algomaster.io/learn/lld/state
 * The State Design Pattern is a behavioral design pattern
 * that lets an object change its behavior when its internal state changes, as
 * if it were switching to a different class at runtime.
 *
 * 1. An object can be in one of many distinct states, each with different behavior.
 * 2. The object’s behavior depends on current context, and that context changes over time.
 * 3. You want to avoid large, monolithic if-else or switch statements that check for every possible state.
 *
 * The State Pattern solves this by encapsulating each state into its own class,
 * and letting the context object delegate behavior to the current state object.
 * This makes your code easier to extend, reuse, and
 * maintain without cluttering the core logic with conditionals.
 * */
public class StateDesignPattern {
    /**
     * Imagine you're building a simple vending machine system. On the surface,
     * it seems like a straightforward task: accept money, dispense products,
     * and go back to idle.
     *
     * At any given time, the vending machine can only be in one state, such as:
     * 1. IdleState
     * 2. ItemSelectedState
     * 3. HasMoneyState
     * 4. DispensingState – The machine is actively dispensing the item.
     *
     *
     * Machine supports following behaviors:
     * 1. selectItem(String itemCode) – Select an item to purchase
     * 2. insertCoin(double amount) – Insert payment for the selected item
     * 3. dispenseItem() – Trigger the item dispensing process
     *
     * Calling dispenseItem() while the machine is in IdleState
     * (no item selected, no money inserted)
     * should do nothing or show an error.
     *
     * Calling insertCoin() before selecting an item might be disallowed or
     * queued.
     *
     * Calling selectItem() during DispensingState should be
     * ignored or deferred until the item is dispensed.
     * */

    static class StateDesignPatternNaive{
        static class VendingMachine{
            private enum State {
                IDLE, ITEM_SELECTED, HAS_MONEY, DISPENSING
            }

            private State currentState = State.IDLE;
            private String selectedItem = "";
            private double insertedAmount = 0.0;

            public void selectItem(String itemCode){
                switch (currentState){
                    case IDLE:
                        selectedItem = itemCode;
                        currentState = State.ITEM_SELECTED;
                        break;
                    case ITEM_SELECTED:
                        System.out.println("Item already selected");
                        break;
                    case HAS_MONEY:
                        System.out.println("Payment already received for item");
                        break;
                    case DISPENSING:
                        System.out.println("Currently dispensing");
                        break;
                }
            }

            public void insertCoin(double amount) {
                switch (currentState) {
                    case IDLE:
                        System.out.println("No item selected");
                        break;
                    case ITEM_SELECTED:
                        insertedAmount = amount;
                        System.out.println("Inserted $" + amount + " for item");
                        currentState = State.HAS_MONEY;
                        break;
                    case HAS_MONEY:
                        System.out.println("Money already inserted");
                        break;
                    case DISPENSING:
                        System.out.println("Currently dispensing");
                        break;
                }
            }

            public void dispenseItem() {
                switch (currentState) {
                    case IDLE:
                        System.out.println("No item selected");
                        break;
                    case ITEM_SELECTED:
                        System.out.println("Please insert coin first");
                        break;
                    case HAS_MONEY:
                        System.out.println("Dispensing item '" + selectedItem + "'");
                        currentState = State.DISPENSING;

                        // Simulate delay and completion
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        System.out.println("Item dispensed successfully.");
                        resetMachine();
                        break;
                    case DISPENSING:
                        System.out.println("Already dispensing. Please wait.");
                        break;
                }
            }

            private void resetMachine() {
                selectedItem = "";
                insertedAmount = 0.0;
                currentState = State.IDLE;
            }
        }

        /** What's Wrong with This Approach?
         * 1. Cluttered Code
         * 2. Hard to Extend
         * 3. Violates the Single Responsibility Principle
         * */
    }

    /**
     * What We Really Need?
     * We need to encapsulate the behavior associated
     * with each state into its own class —
     * so the vending machine can delegate work
     * to the current state object instead of managing it all internally.
     *
     * The State Pattern
     * The State pattern allows an object (the Context) to alter its
     * behavior when its internal state changes. The object appears to
     * change its class because its behavior
     * is now delegated to a different state object.
     *
     * 1. Define a State interface (or abstract class) that
     * declares methods representing
     * the actions the Context can perform.
     *
     * Create ConcreteState classes, each implementing the State interface.
     * Each ConcreteState class
     * implements the behavior specific to that particular
     * state of the Context.
     *
     * The Context class maintains an instance of a
     * ConcreteState subclass that defines its current state.
     *
     * When an action is invoked on the Context,
     * it delegates that action to its current State object.
     *
     * ConcreteState objects are often
     * responsible for transitioning the Context to a new state.
     * */

    static class StateDesignPatternImpl{
        interface MachineState {
            void selectItem(VendingMachine context,String itemCode);
            void insertCoin(VendingMachine context,double amount);
            void dispenseItem(VendingMachine context);
        }

        static class VendingMachine{

            private String selectedItem = "";
            private double insertedAmount = 0.0;

            private MachineState state;

            public VendingMachine() {
                this.state = new IdleState();
            }

            public void setState(MachineState state) {
                this.state = state;
            }

            public void setSelectedItem(String selectedItem) {
                this.selectedItem = selectedItem;
            }

            public void setInsertedAmount(double insertedAmount) {
                this.insertedAmount = insertedAmount;
            }

            public String getSelectedItem() {
                return selectedItem;
            }

            public double getInsertedAmount() {
                return insertedAmount;
            }

            public MachineState getState() {
                return state;
            }

            public void reset(){
                this.selectedItem = "";
                this.insertedAmount = 0.0;
                this.state = new IdleState();
            }

            public void selectItem(String a1) {
                state.selectItem(this, a1);
            }

            public void insertCoin(double v) {
                state.insertCoin(this, v);
            }

            public void dispenseItem() {
                state.dispenseItem(this);
            }
        }

        static class IdleState implements MachineState{

            @Override
            public void selectItem(VendingMachine context, String itemCode) {
                System.out.println("Item selected: " + itemCode);
                context.setSelectedItem(itemCode);
                context.setState(new ItemSelectedState());
            }

            @Override
            public void insertCoin(VendingMachine context, double amount) {
                System.out.println("Please select an item before inserting coins.");
            }

            @Override
            public void dispenseItem(VendingMachine context) {
                System.out.println("Please select an item before inserting coins.");
            }
        }

        static class ItemSelectedState implements MachineState{

            @Override
            public void selectItem(VendingMachine context, String itemCode) {
                System.out.println("Item already selected: " + context.getSelectedItem());
            }

            @Override
            public void insertCoin(VendingMachine context, double amount) {
                System.out.println("Inserted $" + amount + " for item: " + context.getSelectedItem());
                context.setInsertedAmount(amount);
                context.setState(new HasMoneyState());
            }

            @Override
            public void dispenseItem(VendingMachine context) {
                System.out.println("Insert coin before dispensing.");
            }
        }

        static class HasMoneyState implements MachineState{

            @Override
            public void selectItem(VendingMachine context, String itemCode) {
                System.out.println("Cannot change item after inserting money.");
            }

            @Override
            public void insertCoin(VendingMachine context, double amount) {
                System.out.println("Money already inserted.");
            }

            @Override
            public void dispenseItem(VendingMachine context) {
                System.out.println("Dispensing item: " + context.getSelectedItem());
                context.setState(new DispensingState());

                // Simulate dispensing
                try { Thread.sleep(1000); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Item dispensed successfully.");
                context.reset();
            }
        }

        static class DispensingState implements MachineState{

            @Override
            public void selectItem(VendingMachine context, String itemCode) {
                System.out.println("Please wait, dispensing in progress.");
            }

            @Override
            public void insertCoin(VendingMachine context, double amount) {
                System.out.println("Please wait, dispensing in progress.");
            }

            @Override
            public void dispenseItem(VendingMachine context) {
                System.out.println("Already dispensing. Please wait.");
            }
        }
    }

    public static void main(String[] args) {
        StateDesignPatternNaive.VendingMachine vm = new StateDesignPatternNaive.VendingMachine();
        vm.selectItem("122");
        vm.insertCoin(100.0);
        vm.dispenseItem();

        StateDesignPatternImpl.VendingMachine vm1 = new StateDesignPatternImpl.VendingMachine();
        vm1.insertCoin(1.0); // Invalid in IdleState

        vm1.selectItem("A1");
        vm1.insertCoin(1.5);
        vm1.dispenseItem();

        System.out.println("\n--- Second Transaction ---");
        vm1.selectItem("B2");
        vm1.insertCoin(2.0);
        vm1.dispenseItem();
    }
}
