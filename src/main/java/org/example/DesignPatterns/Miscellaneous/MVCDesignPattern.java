package org.example.DesignPatterns.Miscellaneous;

/**
 * https://algomaster.io/learn/lld/mvc
 * MVC Design Pattern (Model–View–Controller)
 *
 * MVC is an architectural design pattern that separates an application into three parts:
 * 1. Model → Data + business rules
 * 2. View → What the user sees
 * 3. Controller → Handles user actions and coordinates Model & View
 *
 * Separation of concerns.
 *
 * Core Components:
 *
 * 1. Model: Represents application data, Contains business rules, Independent of UI. Examples: User, Order
 * 2. View: Displays data to the user, No business logic, Can be UI, webpage, mobile screen, or even CLI. Example: HTML page
 * 3. Controller: Handles user input, Calls Model methods, Selects which View to show Example: Button click handler
 *
 *
 * Real life Example:
 *
 * 1. E-Commerce Application 🛒 (Amazon / Flipkart)
 * Model: Order, User, Cart, Product, Payment
 * View: Product Listing, Cart Page, Checkout Page
 * Controller: Add to Cart, Place Order, Make Payment
 *
 * 2. Banking System 🏦
 * Model: Account, User, Payment, Transaction Rules.
 * View: Account Dashboard, Statements
 * Controller: Transfer Money, Check Balance.
 *
 * 3. Social Media App 📱 (Instagram)
 * Model: Account, Media, Profile, Comment, Post,Like
 * VIEW: Feed, Comments Section
 * Controller: Like Post, Upload Photo, add Comment
 *
 * MVC separates data (Model), UI (View), and
 * request handling (Controller).
 * Controller updates the model and decides which view to render.
 * */
public class MVCDesignPattern {

    static public class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class UserView {
        public void showUser(User user){
            System.out.println(user.toString());
        }
    }

    static class UserController {
        private User model;
        private UserView view;

        public UserController(User model, UserView view) {
            this.model = model;
            this.view = view;
        }

        public void updateUserName(String name) {
            model = new User(model.getId(), name);
        }

        public void displayUser(){
            view.showUser(model);
        }

    }

    public static void main(String[] args) {
        User  model = new User(1, "Vivek");
        UserView view = new UserView();
        UserController userController = new UserController(model, view);

        userController.displayUser();
    }

}
