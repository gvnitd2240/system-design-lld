package org.example.DesignPatterns.Creational;

/***
 * https://algomaster.io/learn/lld/abstract-factory
 * The Abstract Factory Design Pattern is a creational pattern that provides
 * an interface for creating families of related or dependent
 * objects without specifying their concrete classes.
 * -----
 * The Abstract Factory Pattern encapsulates object creation into factory interfaces.
 * ----
 * Each concrete factory implements the interface and
 * produces a complete set of related objects. This ensures
 * that your code remains extensible, consistent, and
 * loosely coupled to specific product implementations.
 */
public class AbstractFactoryDesignPattern {

    /**
     * 1. The Problem: Platform-Specific UI
     * Imagine you're building a cross-platform desktop application
     * that must support both Windows and macOS.
     *  ---
     *  Buttons
     * Checkboxes
     * Text fields
     * Menus
     * */

    /* Naive Approach */


    static class NaiveApproach {
        static class WindowButton{
            public void paint() {
                System.out.println("Painting a Windows-style button.");
            }

            public void onClick() {
                System.out.println("Windows button clicked.");
            }
        }

        static class WindowsCheckbox {
            public static void paint() {
                System.out.println("Painting a Windows-style checkbox.");
            }

            public static void onSelect() {
                System.out.println("Windows checkbox selected.");
            }
        }

        static class MacOSButton {
            public void paint() {
                System.out.println("Painting a macOS-style button.");
            }

            public void onClick() {
                System.out.println("macOS button clicked.");
            }
        }

        static class MacOSCheckbox {
            public void paint() {
                System.out.println("Painting a macOS-style checkbox.");
            }

            public void onSelect() {
                System.out.println("macOS checkbox selected.");
            }
        }

        public static void main(String[] args) {
            String os = System.getProperty("os.name");

            if(os.contains("windows")){
                WindowButton windowButton = new WindowButton();
                windowButton.onClick();
                windowButton.paint();
            }  else if (os.contains("Mac")) {
                MacOSButton button = new MacOSButton();
                MacOSCheckbox checkbox = new MacOSCheckbox();
                button.paint();
                checkbox.paint();
            }
        }

        /**
         * Why This Approach Breaks Down
         * 1. Tight Coupling to Concrete Classes - check the OS manually.
         * 2. No Abstraction or Polymorphism
         * 3. Code Duplication and Repetition
         * 4. Scalability Issues
         * 5. Violation of Open/Closed Principle
         * */


    }

    /**
     * What We Really Need
     * Group related components
     * Encapsulate platform-specific creation logic into a factory
     * Work with UI components polymorphically, regardless of platform
     * Easily add new families of UI elements without modifying the application’s core logic
     * */

    public static class AbstractFactoryDesignPatternImpl {
        /**
         * 1. Abstract Factory (GUIFactory)
         * 2. Concrete Factory (WindowsFactory, MacOSFactory)
         * 3. Abstract Product (Button, Checkbox)
         * 4. Concrete Product (WindowsButton, MacOSCheckbox)
         * 5. Client (Application)
         * */

        interface Button{
            public void paint();
            public void onClick();
        }

        interface Checkbox{
            public void paint();
            public void onSelect();
        }

        public static class WindowButton implements Button{

            @Override
            public void paint() {

            }

            @Override
            public void onClick() {

            }
        }


        public static class MacosButton implements Button{

            @Override
            public void paint() {
                System.out.println("Painting a macOS-style button.");
            }

            @Override
            public void onClick() {
                System.out.println("MacOS button clicked.");
            }
        }

        public static class WindowCheckBox implements Checkbox{

            @Override
            public void paint() {

            }

            @Override
            public void onSelect() {
            }
        }

        public static class MacOsCheckBox implements  Checkbox{

            @Override
            public void paint() {
                System.out.println("Painting a macOS-style checkbox.");
            }

            @Override
            public void onSelect() {
                System.out.println("MacOS checkbox selected.");
            }
        }

        interface GUIFactory {
            Button createButton();
            Checkbox createCheckbox();
        }

        static class WindowFactory implements GUIFactory{

            @Override
            public Button createButton() {
                return new WindowButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new WindowCheckBox();
            }
        }

        static class MacOsFactory implements GUIFactory{

            @Override
            public Button createButton() {
                return new MacosButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new MacOsCheckBox();
            }
        }

        public static class Application {
            private final Button button;
            private final Checkbox checkbox;

            public Application(GUIFactory guiFactory){
                this.button = guiFactory.createButton();
                this.checkbox = guiFactory.createCheckbox();
            }

            public void renderUI(){
                button.paint();
                checkbox.paint();
            }

        }
    }

    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        AbstractFactoryDesignPatternImpl.GUIFactory factory;
        if(os.contains("windows")){
            factory =new AbstractFactoryDesignPatternImpl.WindowFactory();
        } else {
            factory = new AbstractFactoryDesignPatternImpl.MacOsFactory();
        }

        AbstractFactoryDesignPatternImpl.Application application = new AbstractFactoryDesignPatternImpl.Application(factory);

        application.renderUI();

    }

    /**
     * GUIFactory
     * WindowsFactory, MacOSFactory
     * Button, CheckBox
     * Button -> Windows, Macos
     * CheckBox -> Windows, Macos
     *
     * Application -> GUIFactory -> renderUI.
     * Client -> Application -> ----
     * */


}
