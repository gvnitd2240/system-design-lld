package org.example.DesignPatterns.Behavior;

import java.util.Stack;

/**
 * https://algomaster.io/learn/lld/command
 * The Command Design Pattern is a behavioral pattern
 * that turns a request into a standalone object,
 * allowing you to parameterize actions,
 * queue them, log them, or support undoable operations
 * all while decoupling the sender from the receiver.
 *
 * It’s particularly useful in situations where:
 *
 * You want to encapsulate operations as objects.
 * You need to queue, delay, or log requests.
 * You want to support undo/redo functionality.
 * You want to decouple the object that invokes an operation from the
 * one that knows how to perform it.
 * */
public class CommandDesignPattern {
    /**
     * 1. The Problem: The Tightly Coupled Smart Home Controller
     *
     * Imagine you're building a "Smart Home Hub" application.
     * This hub needs to control various devices:
     *
     * 💡 Smart lights
     * 🌡️ Thermostats
     * 🔒 Security systems
     * 🔊 Smart speakers
     * 🚪 Garage doors
     *
     * The hub should be able to send commands like:
     *
     * light.on(), light.off()
     * thermostat.setTemperature(22)
     * speaker.playMusic()
     * */

    static class CommandDesignPatternNaive{
        static class Lights{

            public void on(){
                System.out.println("light is on.");
            }

            public void off(){
                System.out.println("light is off.");
            }
        }

        static class Thermostats{
            private static int temperature;

            public static void setTemperature(int temperature) {
                Thermostats.temperature = temperature;
            }
        }

        static class SmartHomeControllerV1 {
            private final Lights light;
            private final Thermostats thermostat;

            SmartHomeControllerV1(Lights light, Thermostats thermostat) {
                this.light = light;
                this.thermostat = thermostat;
            }

            public void turnOnLight() {
                light.on();
            }

            public void turnOffLight() {
                light.off();
            }

            public void setThermostatTemperature(int temperature) {
                thermostat.setTemperature(temperature);
                System.out.println("Temperature is set to " + temperature);
            }
        }

        public static void execute() {
            Lights light = new Lights();
            Thermostats thermostat = new Thermostats();
            SmartHomeControllerV1 smartHomeControllerV1 = new SmartHomeControllerV1(light, thermostat);
            smartHomeControllerV1.turnOnLight();
            smartHomeControllerV1.turnOffLight();
            smartHomeControllerV1.setThermostatTemperature(22);
        }

        /** Why This Design Fails as the System Grows?
         * 1. Tight Coupling
         * 2. Poor Scalability
         * 3. No Undo/Redo Support:
         *      There's no way to reverse a command. Want to implement an “undo last action” feature? You’d need to:
         *      Track device states manually
         *      Write custom undo logic for every method
         *      Add a large switch/if-else block somewhere to figure out what action to reverse
         * 4. No Scheduling or Queuing - "Turn on the lights at 7:00 PM"
         * 5. No Generic Logging
         * 6. Difficult UI Mapping - Or a mobile app with customizable scenes like "Movie Night" or "Leave Home".
         * */
    }

    /**
     * What We Really Need?
     * We need to treat each command (e.g., “turn on light”, “set thermostat to 22°C”)
     * as a standalone object — something that encapsulates:
     *
     * What to do
     * Which device it affects
     * How to execute it
     * (Optionally) How to undo it
     * */

    /**
     * Command Pattern
     * The Command Design Pattern is a behavioral pattern that turns a request into
     * a standalone object, allowing you to:
     * 1. Parameterize actions
     * 2. Queue or log operations
     * 3. Support undo/redo
     * 4. Decouple the invoker of an operation from the receiver that performs it
     * ---
     * each request (like light.on() or thermostat.setTemperature(22)) as a Command object.
     * */

    /**
     * Components
     * 1. Command (Interface): Defines a standard interface for executing operations — typically an execute() method.
     *    May also declare an undo() method if undo functionality is required.
     *    Serves as the abstraction that all concrete commands will implement.
     * 2. ConcreteCommand:
     *    Implements the Command interface.
     *    Maintains a reference to the Receiver, and implements execute() by delegating to the receiver’s method(s).
     * 3. Receiver
     *    The object that performs the actual work.
     *    It contains the business logic that will be triggered by a command.
     *    The receiver is not aware of the command or the invoker — it simply exposes the operations.
     * 4. Invoker
     *    Responsible for initiating command execution.
     *    It holds a reference to a Command object and calls execute() when an action (e.g., button press) occurs.
     *    The invoker does not know the specifics of the command or the receiver — it interacts only with the Command interface.
     * 5. Client
     *    Creates instances of ConcreteCommand, associating each with the appropriate Receiver.
     *    Configures the Invoker with the command(s) to execute.
     *    The client decides what actions to perform and sets everything up — but does not execute the commands itself.
     *
     * How it works?
     * Each Command implements a standard interface like execute() (and optionally undo())
     * The Invoker (e.g., remote control, scheduler, UI) simply calls command.execute()
     * The Receiver (e.g., Light, Thermostat) performs the actual operation when the command is executed
     * */

    static class CommandDesignPatternImpl{
        interface  Command{
            public void execute();
            void undo();
        }

        static class Light{
            public void on() {
                System.out.println("Light turned ON");
            }

            public void off() {
                System.out.println("Light turned OFF");
            }
        }

        static class Thermostat {
            private int currentTemperature = 20;

            public void setTemperature(int temp) {
                System.out.println("Thermostat set to " + temp + "°C");
                currentTemperature = temp;
            }

            public int getCurrentTemperature() {
                return currentTemperature;
            }
        }

        static class LightOnCommand implements Command{
            private final Light light;

            LightOnCommand(Light light) {
                this.light = light;
            }

            @Override
            public void execute() {
                light.on();
            }

            @Override
            public void undo() {
                light.off();
            }
        }

        static class LightOffCommand implements Command{
            private final Light light;

            LightOffCommand(Light light) {
                this.light = light;
            }

            @Override
            public void execute() {
                light.off();
            }

            @Override
            public void undo() {
                light.on();
            }
        }

        static class SetTemperatureCommand implements Command{
            private final Thermostat thermostat;
            private final int newTemperature;
            private int previousTemperature;

            SetTemperatureCommand(Thermostat thermostat, int newTemperature) {
                this.thermostat = thermostat;
                this.newTemperature = newTemperature;
            }

            @Override
            public void execute() {
                previousTemperature = thermostat.getCurrentTemperature();
                thermostat.setTemperature(newTemperature);
            }

            @Override
            public void undo() {
                thermostat.setTemperature(previousTemperature);
            }
        }

        /*Invoker*/
        static class SmartButton{
            private Command currentCommand;
            private final Stack<Command> history = new Stack<>();

            public void setCommand(Command command) {
                this.currentCommand = command;
            }

            public void press(){
                if(currentCommand != null){
                    currentCommand.execute();
                    history.push(currentCommand);
                } else {
                    System.out.println("No command assigned.");
                }
            }

            public void undoLast(){
                if(!history.isEmpty()){
                    Command lastCommand = history.pop();
                    lastCommand.undo();
                } else {
                    System.out.println("Nothing to undo.");
                }
            }
        }

        public static class SmartHomeApp{
            public static void execute() {
                SmartButton button = new SmartButton();

                Light light  = new Light();

                LightOnCommand lightOnCommand = new LightOnCommand(light);
                LightOffCommand lightOffCommand = new LightOffCommand(light);
                Thermostat thermostat = new Thermostat();

                Command setTemp22 = new SetTemperatureCommand(thermostat, 22);

                System.out.println("→ Pressing Light ON");
                button.setCommand(lightOnCommand);
                button.press();

                System.out.println("→ Pressing Set Temp to 22°C");
                button.setCommand(setTemp22);
                button.press();

                System.out.println("→ Pressing Light OFF");
                button.setCommand(lightOffCommand);
                button.press();

                // Undo sequence
                System.out.println("\n↶ Undo Last Action");
                button.undoLast();  // undo Light OFF

                System.out.println("↶ Undo Previous Action");
                button.undoLast();  // undo Set Temp

                System.out.println("↶ Undo Again");
                button.undoLast();  // undo Light ON
            }
        }
    }

    public static void main(String[] args) {
        CommandDesignPatternNaive.execute();
        CommandDesignPatternImpl.SmartHomeApp.execute();
    }

    /**
     * Command -> execute, undoLast
     * Concreate Commands -> Lightoncommand, lightoff -> Receiver
     * Receiver -> Light, Thermostat.
     * Invoker -> Remote, Smart Mobile app.
     * Client -> Invoker -> Command -> Receiver -> Execute.s
     * */

    /**
     * What We Achieved?
     * Encapsulated Commands
     * Decoupled UI/Logic -> Invoker does not need to know the how a command works
     * Undo Support -> Each command tracks and reverts its own effect.
     * Extensibility: Easily add PlayMusicCommand, OpenGarageCommand, etc.
     * History Tracking: Command history enables undo/redo or logging
     * */
}

