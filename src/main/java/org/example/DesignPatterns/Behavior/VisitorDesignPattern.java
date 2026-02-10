package org.example.DesignPatterns.Behavior;

import java.util.List;

/**
 * https://algomaster.io/learn/lld/visitor
 * The Visitor Design Pattern is a behavioral pattern
 * that lets you add new operations to existing object
 * structures without modifying their classes.
 *
 * It achieves this by allowing you to separate the
 * algorithm from the objects it operates on.
 *
 * It’s particularly useful in situations where:
 * 1. You have a complex object structure
 * (like ASTs, documents, or UI elements)
 * that you want to perform multiple unrelated operations on.
 *
 * 2. You want to add new behaviors to classes without changing their source code.
 * 3. You need to perform different actions depending on
 *    an object’s concrete type,
 *    without resorting to a long chain of if-else or instanceof checks.
 *
 *
 * The Visitor Pattern lets you externalize operations into separate
 * visitor classes. Each visitor implements behavior for every
 * element type, while the elements simply accept the visitor.
 * This keeps your data structures clean and your logic modular and extensible.
 * */
public class VisitorDesignPattern {
    /**
     * 1. The Problem: Adding Operations to a Shape Hierarchy
     * Imagine you’re building a vector graphics editor that supports multiple shape types:
     * Rectangle
     * Circle
     * Triangle
     *
     * Each shape is part of a common hierarchy and must support a variety of operations, such as:
     *
     * Rendering on screen
     * Calculating area
     * Exporting to SVG
     * Serializing to JSON
     * */

    static class VisitorDesignPatternNaive{
        interface Shape{
            void draw();
            double calculateArea();
            String exportAsSvg();
            String toJson();
        }

        static class Circle implements Shape{
            private double radius;

            Circle(double radius) {
                this.radius = radius;
            }

            @Override
            public void draw() {
                System.out.println("Circle Drawing");
            }

            @Override
            public double calculateArea() {
                return Math.PI * radius * radius;
            }

            @Override
            public String exportAsSvg() {
                return "<circle r=\"" + radius + "\" />";
            }

            @Override
            public String toJson() {
                return "{ \"type\": \"circle\", \"radius\": " + radius + " }";
            }
        }

        /**Why This Breaks Down?
         * 1. Violates the Single Responsibility Principle
         * 2. Hard to Extend
         * 3. You Don’t Always Control the Classes
         * */
    }

    /** What We Really Need?
     * Separate operations from the shape classes
     * Add new behaviors without modifying existing classes
     * Avoid duplicating instanceof checks or using type switches to handle different shapes
     * */

    /**
     * Visitor Pattern: The Visitor Design Pattern lets you separate
     * algorithms from the objects on which they operate.
     * It enables you to add new operations to
     * a class hierarchy without modifying the classes themselves.
     *
     * This is especially helpful when you have:
     * 1. A stable set of element classes (e.g., shapes)
     * 2. A growing set of operations that need to work across those classes
     * (e.g., rendering, exporting, calculating)
     *
     * 1. Element Interface (e.g., Shape) - Declares a single method: void accept(Visitor visitor);
     * 2. Concrete Elements (e.g., Circle, Rectangle): Inside the accept() method, they call back the visitor’s corresponding method using visitor.visitX(this).
     * 3. Visitor Interface: Declares a set of visit() methods — one for each concrete element type.Declares a set of visit() methods — one for each concrete element type.
     * 4. Concrete Visitors (e.g., AreaCalculatorVisitor)
     * */

    static class VisitorDesignPatternImpl{
        interface Shape{
            void accept(Visitor visitor);
        }

        static class Circle implements Shape{
            private final double radius;

            Circle(double radius) {
                this.radius = radius;
            }

            public double getRadius() {
                return radius;
            }

            @Override
            public void accept(Visitor visitor) {
                visitor.visitCircle(this);
            }
        }

        static class Rectangle implements Shape{
            private final double length;
            private final double breadth;

            Rectangle(double length, double breadth) {
                this.length = length;
                this.breadth = breadth;
            }

            public double getLength() {
                return length;
            }

            public double getBreadth() {
                return breadth;
            }

            @Override
            public void accept(Visitor visitor) {
                visitor.visitRectangle(this);
            }
        }

        interface Visitor{
            void visitCircle(Circle circle);
            void visitRectangle(Rectangle rectangle);
        }

        static class AreaCalculatorVisitor implements Visitor {

            @Override
            public void visitCircle(Circle circle) {
                double area = Math.PI * circle.getRadius() * circle.getRadius();
                System.out.println("Area of Circle: " + area);
            }

            @Override
            public void visitRectangle(Rectangle rectangle) {
                double area = rectangle.getBreadth() * rectangle.getLength();
                System.out.println("Area of Rectangle: " + area);
            }
        }


        static class SVGExporterVisitor implements Visitor{

            @Override
            public void visitCircle(Circle circle) {
                System.out.println("<circle r=\"" + circle.getRadius() + "\" />");
            }

            @Override
            public void visitRectangle(Rectangle rectangle) {
                System.out.println("<rect width=\"" + rectangle.getBreadth() +
                        "\" height=\"" + rectangle.getLength() + "\" />");
            }
        }
    }

    public static void main(String[] args) {
        List<VisitorDesignPatternImpl.Shape> shapes = List.of(
                new VisitorDesignPatternImpl.Circle(5),
                new VisitorDesignPatternImpl.Rectangle(10, 4),
                new VisitorDesignPatternImpl.Circle(2.5)
        );

        System.out.println("=== Calculating Areas ===");
        VisitorDesignPatternImpl.Visitor areaCalculator = new VisitorDesignPatternImpl.AreaCalculatorVisitor();
        for (VisitorDesignPatternImpl.Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        System.out.println("\n=== Exporting to SVG ===");
        VisitorDesignPatternImpl.Visitor svgExporter = new VisitorDesignPatternImpl.SVGExporterVisitor();
        for (VisitorDesignPatternImpl.Shape shape : shapes) {
            shape.accept(svgExporter);
        }


        /**What We Achieved?
         * Decoupled logic: Shape classes are clean; logic lives in visitors
         * Open/Closed Principle: Easily add new visitors (e.g., JsonExporterVisitor) without touching shapes
         * Double dispatch: Eliminated need for instanceof or type-checking
         * Reusability & maintainability: Each visitor focuses on one operation and is testable in isolation
         * */

    }

}
