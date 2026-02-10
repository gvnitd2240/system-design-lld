package org.example.DesignPatterns.Structural;

/**
 * https://algomaster.io/learn/lld/decorator
 * The Decorator Design Pattern is a structural pattern that
 * lets you dynamically add new behavior or responsibilities
 * to objects without modifying their underlying code.
 *  ---
 *  1. extend the functionality of a class without subclassing it.
 *  2. compose behaviors at runtime, in various combinations.
 *  3. avoid bloated classes filled with if-else logic
 * */
public class DecoratorDesignPattern {
    /**
     * 1. The Problem: Adding Features to a Text Renderer
     * Imagine you’re building a rich text rendering system
     * (like a simple word processor or a markdown preview tool).
     * At the core of your system is a TextView component
     * that renders plain text on screen.
     *
     * Soon, product requirements evolve:
     * You need to support bold text
     * Then italic text
     * Then underlined text
     * Then scrollable and bordered text containers
     * And possibly combinations of those (e.g., bold + italic + underlined)
     * */

    static class DecoratorDesignPatternNaiveApproach{
        interface TextView{
            void render();
        }

        static class BoldTextView implements TextView{

            @Override
            public void render() {
                System.out.println("Bold.");
            }
        }

        static class ItalicTextView implements TextView{

            @Override
            public void render() {
                System.out.println("Italic.");
            }
        }

        static class BoldItalicTextView implements TextView {
            @Override
            public void render() {
                System.out.print("Rendering bold + italic text");
            }
        }

        /**
         * Why This Approach Quickly Falls Apart?
         *
         * 1. Class Explosion
         * 2. Rigid Design - changing features at runtime. want to
         *    turn on or off based on user preference.
         *    Will need conditional logic or object swapping.
         * 3. Violates the Open/Closed Principle
         *
         * */

    }

    /**
     * What We Really Need?
     * 1. Add features like bold, italic, underline dynamically and independently
     * 2. Compose features in flexible combinations (e.g., bold + scrollable + bordered)
     * 3. Avoid creating dozens of subclasses for every possible variation
     * 4. Follow the Open/Closed Principle — open for extension, closed for modification
     * */

    /**
     * The Decorator Pattern allows you to add responsibilities
     * to objects dynamically, without altering
     * their structure or modifying their original code.
     *
     * At its core, the pattern relies on wrapping an object
     * inside another object (a decorator) that implements
     * the same interface and adds new behavior
     * before or after delegating to the wrapped object.
     *
     * layered effect -> where decorators can be stacked to apply multiple enhancements
     *
     * 1. Component -> TextView -> Declares the common interface (execute()) for all core and decorated objects
     * 2. ConcreteComponent -> PlainTextView -> The base object that can be dynamically decorated
     * 3. BaseDecorator(abstract) -> Implements the component interface and stores a reference to the component to be decorated
     * 4. ConcreteDecorators (BoldDecorator, ItalicDecorator, etc.): Extend the base decorator to add new functionality before/after calling the wrapped component’s method
     * */

    public class DecoratorDesignPatternImpl{
        interface TextView{
            void render();
        }

        static class PlainTextView implements TextView{
            private final String text;

            PlainTextView(String text){
                this.text = text;
            }

            @Override
            public void render() {
                System.out.println(text);
            }
        }

        static abstract class TextDecorator implements TextView{
            protected final TextView inner;

            public TextDecorator(TextView inner){
                this.inner = inner;
            }
        }


        static class BoldDecorator extends TextDecorator{
            public BoldDecorator(TextView inner){
                super(inner);
            }

            @Override
            public void render() {
                System.out.print("<b>");
                inner.render();
                System.out.print("</b>");
            }
        }


        static class ItalicDecorator extends TextDecorator{
            public ItalicDecorator(TextView inner){
                super(inner);
            }

            @Override
            public void render() {
                System.out.print("<i>");
                inner.render();
                System.out.print("</i>");
            }
        }


        static class UnderlineDecorator extends TextDecorator{
            public UnderlineDecorator(TextView inner){
                super(inner);
            }

            @Override
            public void render() {
                System.out.print("<u>");
                inner.render();
                System.out.print("</u>");
            }
        }
        public static class TextRendererApp {
            public static void execute() {
                TextView text = new PlainTextView("Hello, World!");
                System.out.print("Plain.");

                text.render();

                System.out.print("Bold.");
                TextView boldText = new BoldDecorator(text);
                boldText.render();
                System.out.println();

                System.out.print("Bold + Italic");
                TextView boldItalicText = new ItalicDecorator(new BoldDecorator(text));
                boldItalicText.render();
                System.out.println();

                System.out.print("Bold + Italic + Underline");
                TextView boldItalicUnderlineText = new UnderlineDecorator(new ItalicDecorator(new BoldDecorator(text)));
                boldItalicUnderlineText.render();
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        DecoratorDesignPatternImpl.TextRendererApp app = new DecoratorDesignPatternImpl.TextRendererApp();
        app.execute();
    }

    /**
     * Component Interface -> TextView
     * 2. Implement the Concrete Component -> PlainTextView
     * 3. Create the Abstract Decorator
     *      3.1. implements TextView
     *      3.2. holds a reference to another TextView component.
     *      3.3. It forwards calls to the wrapped component.
     * 4. Implement Concrete Decorators -
     *       Each decorator adds a specific formatting layer before or after
     *       calling the wrapped component's render() method.
     * */

    /**
     * What We Achieved?
     * 1. Dynamic layering
     * 2. Modular design
     * 3. No class explosion
     * 4. Open/Closed Principle
     * */

    /**
     * Possible Examples:
     * Pizza Shop
     * Coffee Shop
     * If you have a choice that you can make on the top of another. - Decorator Design Pattern
     * */
}
