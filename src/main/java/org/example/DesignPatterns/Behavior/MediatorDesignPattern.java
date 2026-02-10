package org.example.DesignPatterns.Behavior;

/**
 * https://algomaster.io/learn/lld/mediator
 * The Mediator Design Pattern is a behavioral
 * pattern that defines an object (the Mediator)
 * to encapsulate how a set of objects interact.
 *
 * It promotes loose coupling by preventing objects from referring to each other directly,
 * and lets you vary their interactions independently.
 *
 * You have a group of tightly coupled classes or
 * UI components that need to communicate.
 *
 * Changes in one component require updates in multiple others.
 *
 * You want to centralize communication logic to simplify maintenance and testing.
 *
 * The Mediator Pattern solves this by introducing a central object that handles communication between
 * components. Each component interacts only with the mediator,
 * which coordinates and manages interactions — reducing coupling
 * and simplifying each component’s logic.
 *
 * */
public class MediatorDesignPattern {
    /**
     * 1. The Problem: Tightly Coupled UI Components
     * Imagine you're building a login form with the following UI components:
     * A username field
     * A password field
     * A login button
     * A status label
     *
     * The login button should be enabled only when both username and password fields are non-empty.
     * When the button is clicked, it should attempt login and display the result in the status label.
     * */

    static class MediatorDesignPatternNaive{
        static class TextField {
            private String text = "";
            private Button loginButton;

            public void setText(String newText) {
                this.text = newText;
                if(loginButton != null){
                    loginButton.checkEnabled();
                }
            }

            public void setLoginButton(Button loginButton) {
                this.loginButton = loginButton;
            }

            public String getText() {
                return text;
            }
        }

        static class Button{
            private MediatorDesignPatternNaive.TextField usernameField;
            private MediatorDesignPatternNaive.TextField passwordField;
            private Label statusLabel;

            public void setDependencies(MediatorDesignPatternNaive.TextField username, MediatorDesignPatternNaive.TextField password, Label status) {
                this.usernameField = username;
                this.passwordField = password;
                this.statusLabel = status;
            }

            public void checkEnabled() {
                boolean enable = !usernameField.getText().isEmpty() &&
                        !passwordField.getText().isEmpty();
                System.out.println("Login Button is now " + (enable ? "ENABLED" : "DISABLED"));
            }

            public void click() {
                if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    System.out.println("Login successful!");
                    statusLabel.setText("✅ Logged in!");
                } else {
                    System.out.println("Login failed.");
                    statusLabel.setText("❌ Please enter username and password.");
                }
            }

        }

        static class Label{
            public void setText(String message) {
                System.out.println("Status: " + message);
            }
        }

        /**What’s Wrong with This Design?
         * 1. Tight Coupling
         * 2. Lack of Reusability
         * 3. Poor Maintainability
         * 4. Hidden Logic Sprawled Across Components
         * */
    }

    /**
     * What We Really Need?
     * Decouple UI components from each other
     * Centralize the coordination logic so that each
     * component focuses only on its own role
     * Make it easier to add, remove, or change components or
     * interactions without breaking everything else
     * */

    /**
     * The Mediator Pattern
     * The Mediator Pattern promotes loose coupling
     * by centralizing communication between objects. Instead of
     * having components refer to and
     * interact with each other directly, they
     * communicate through a mediator.
     * */

    /**
     * Components:
     *
     * 1. Mediator Interface: Declares a method (commonly notify(), componentChanged(), or send()) used by components to send events to the mediator.
     * 2. ConcreteMediator: Implements the mediator interface and coordinates communication between registered components. It holds references to all components it manages.
     * 3. Component Base Class (optional): An abstract class or interface for UI elements or modules. It holds a reference to the mediator and delegates communication through it.
     * 4. ConcreteComponents: These are the actual components (e.g., TextField, Button, Label) that perform actions and notify the mediator when changes occur.
     *    They never talk directly to each other.
     * */


    static class MediatorDesignPatternImpl {
        interface UIMediator{
            void componentChanged(UIComponent component);
        }


        abstract static class UIComponent{
            protected  UIMediator uiMediator;

            public UIComponent(UIMediator uiMediator) {
                this.uiMediator = uiMediator;
            }

            public void notifyMediator(){
                uiMediator.componentChanged(this);
            }
        }

        static class TextField extends UIComponent {
            private String text = "";

            public TextField(UIMediator uiMediator) {
                super(uiMediator);
            }

            public void setText(String newText) {
                this.text = newText;
                System.out.println("TextField updated: " + newText);
                notifyMediator();
            }

            public String getText() {
                return text;
            }
        }

        static class Button extends UIComponent {
            private boolean enabled = false;

            public Button(UIMediator uiMediator) {
                super(uiMediator);
            }

            public void onClick() {
                if (enabled) {
                    System.out.println("Login Button clicked!");
                    notifyMediator(); // Will trigger login attempt
                } else {
                    System.out.println("Login Button is disabled.");
                }
            }

            public void setEnabled(boolean value) {
                this.enabled = value;
                System.out.println("Login Button is now " + (enabled ? "ENABLED" : "DISABLED"));
            }
        }

        static class Label extends UIComponent {
            private String text;

            public Label(UIMediator mediator) {
                super(mediator);
            }

            public void setText(String message) {
                this.text = message;
                System.out.println("Status: " + text);
            }
        }

        static class FormMediator implements UIMediator{
            private TextField usernameField;
            private TextField passwordField;
            private Button loginButton;
            private Label statusLabel;

            public void setUsernameField(TextField usernameField) {
                this.usernameField = usernameField;
            }

            public void setPasswordField(TextField passwordField) {
                this.passwordField = passwordField;
            }

            public void setLoginButton(Button loginButton) {
                this.loginButton = loginButton;
            }

            public void setStatusLabel(Label statusLabel) {
                this.statusLabel = statusLabel;
            }

            @Override
            public void componentChanged(UIComponent component) {
                if(component == usernameField || component == passwordField){
                    boolean enableButton = !usernameField.getText().isEmpty() &&
                            !passwordField.getText().isEmpty();

                    loginButton.setEnabled(enableButton);
                } else if (component == loginButton){
                    String username = usernameField.getText();
                    String password = passwordField.getText();

                    if ("admin".equals(username) && "1234".equals(password)) {
                        statusLabel.setText("✅ Login successful!");
                    } else {
                        statusLabel.setText("❌ Invalid credentials.");
                    }
                }

            }
        }

        /**
         * What We Achieved?
         * Loose coupling: Components no longer know about each other
         * Separation of concerns: Coordination logic lives in the mediator, not in the components
         * Ease of extension: Add new components or behaviors without modifying existing ones
         * Reusability: Components like TextField, Button, and Label can be reused in other contexts
         * */
    }

    public static void main(String[] args) {
        MediatorDesignPatternImpl.FormMediator uiMediator = new MediatorDesignPatternImpl.FormMediator();
        MediatorDesignPatternImpl.TextField username = new MediatorDesignPatternImpl.TextField(uiMediator);
        MediatorDesignPatternImpl.TextField password = new MediatorDesignPatternImpl.TextField(uiMediator);


        MediatorDesignPatternImpl.Button loginButton = new MediatorDesignPatternImpl.Button(uiMediator);
        MediatorDesignPatternImpl.Label label = new MediatorDesignPatternImpl.Label(uiMediator);

        uiMediator.setUsernameField(username);
        uiMediator.setUsernameField(password);
        uiMediator.setLoginButton(loginButton);
        uiMediator.setStatusLabel(label);

        username.setText("admin");
        password.setText("1234");

        loginButton.onClick();

        System.out.println("\n--- New Attempt with Wrong Password ---");
        password.setText("wrong");
        loginButton.onClick(); // Should fail
    }

}
