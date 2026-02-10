package org.example.DesignPatterns.Structural;

import java.awt.image.CropImageFilter;

/**
 * https://algomaster.io/learn/lld/bridge
 * The Bridge Design Pattern is a structural pattern that
 * lets you decouple an abstraction from its
 * implementation, allowing the two to vary independently.
 *
 * You have classes that can be extended in multiple orthogonal dimensions (e.g., shape vs. rendering technology, UI control vs. platform).
 * You want to avoid a deep inheritance hierarchy that multiplies combinations of features.
 * You need to combine multiple variations of behavior or implementation at runtime.
 *
 * The Bridge Pattern splits a class into two separate hierarchies:
 * 1.One for the abstraction (e.g., shape, UI control)
 * 2. One for the implementation (e.g., rendering engine, platform)
 *
 * These two hierarchies are "bridged" via composition —
 * not inheritance — allowing you to mix and match independently.
 *
 *
 * */
public class BridgeDesignPattern {

    /**
     * 1. The Problem: Drawing Shapes
     *  cross-platform graphics library
     *  rendering shapes like circles and rectangles using different rendering approaches:
     *  🟢 Vector rendering – for scalable, resolution-independent output
     *  🔵 Raster rendering – for pixel-based output
     *
     *  Now, you need to support:
     *  Drawing different shapes (e.g., Circle, Rectangle)
     *  Using different renderers (e.g., VectorRenderer, RasterRenderer)
     * */

    static  class BridgeDesignPatternNaive{
        interface  Shape {
            public abstract void draw();
        }

        static class VectorCircle implements Shape{

            @Override
            public void draw() {
                System.out.println("Drawing VectorCircle.");
            }
        }

        static class RoasterCircle implements Shape{

            @Override
            public void draw() {
                System.out.println("Drawing RoasterCircle.");
            }
        }

        static class RoasterRectangle implements Shape{

            @Override
            public void draw() {
                System.out.println("Drawing RoasterRectangle.");
            }
        }

        static class VectorRectangle implements Shape{

            @Override
            public void draw() {
                System.out.println("Drawing VectorRectangle.");
            }
        }

        public class App {
            public static void main(String[] args) {
                Shape s1 = new VectorCircle();
                Shape s2 = new RoasterRectangle();

                s1.draw(); // Drawing Circle as VECTORS
                s2.draw(); // Drawing Rectangle as PIXELS
            }
        }
        /** Why This Quickly Breaks Down?
         * 1. Class Explosion
         * 2. Tight Coupling
         * 3. Violates Open/Closed Principle
         * */

    }

    /**
     * What We Really Need?
     * Separates the abstraction (Shape) from its implementation (Renderer)
     * Allows new renderers to be added without touching shape classes
     * Enables new shapes to be added without modifying or duplicating renderer logic
     * Keeps the system scalable, extensible, and composable
     *
     * The Bridge Design Pattern lets you split a class into two separate
     * hierarchies — one for the abstraction and another
     * for the implementation — so that they can evolve independently.
     *
     * In the Bridge Pattern, "abstraction has-a implementation" —
     * the abstraction delegates work to an implementor object.
     *
     * 1. Abstraction (e.g., Shape) - Will have Renderer. While constructing the object
     * of object RefinedAbstraction, we will pass the renderer.
     * 2. RefinedAbstraction (e.g., Circle, Rectangle)
     * 3. Implementor (e.g., Renderer)
     * 4. ConcreteImplementors (e.g., VectorRenderer,RasterRenderer)
     * */

    static class BridgeDesignPatternImpl{

        static abstract class  Shape {
            protected Renderer renderer;

            public Shape(Renderer renderer) {
                this.renderer = renderer;
            }

            public abstract void draw();
        }

        static class Circle extends Shape {
            private final float radius;

            Circle(float radius, Renderer renderer) {
                super(renderer);
                this.radius = radius;
            }

            @Override
            public void draw() {
                renderer.renderCircle(radius);
            }
        }

        static class Rectangle extends Shape {
            private final float width;
            private final float length;

            Rectangle(float width,float length,  Renderer renderer) {
                super(renderer);
                this.width = width;
                this.length = length;
            }

            @Override
            public void draw() {
                renderer.renderRectangle(width, length);
            }
        }



        interface Renderer{
            void renderCircle(float radius);
            void renderRectangle(float width, float height);
        }

        static class VectorRenderer implements Renderer{
            @Override
            public void renderCircle(float radius) {
                System.out.println("Drawing a circle of radius " + radius + " using VECTOR rendering.");
            }

            @Override
            public void renderRectangle(float width, float height) {
                System.out.println("Drawing a rectangle " + width + "x" + height + " using VECTOR rendering.");
            }
        }

        static class RastorRenderer implements Renderer{
            @Override
            public void renderCircle(float radius) {
                System.out.println("Drawing a circle of radius " + radius + " using RastorRenderer rendering.");
            }

            @Override
            public void renderRectangle(float width, float height) {
                System.out.println("Drawing a rectangle " + width + "x" + height + " using RastorRenderer rendering.");
            }
        }

        public static class BridgeDemo{
            public static void execute() {
                Renderer vector = new VectorRenderer();
                Renderer raster = new RastorRenderer();

                Shape circle1 = new Circle(5, vector);
                Shape circle2 = new Circle( 5, raster);


                Shape rectangle1 = new Rectangle( 10, 4, vector);
                Shape rectangle2 = new Rectangle( 10, 4, raster);

                circle1.draw();     // Vector
                circle2.draw();     // Raster
                rectangle1.draw();  // Vector
                rectangle2.draw();  // Raster

            }
        }

        public static void main(String[] args) {
            BridgeDemo.execute();
        }

        /** What We Achieved?
         * Decoupled abstractions from implementations
         * Open/Closed compliance:
         * No class explosion
         * Runtime flexibility
         * Clean, extensible design
         * */
    }
}
