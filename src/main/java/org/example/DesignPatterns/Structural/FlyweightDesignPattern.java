package org.example.DesignPatterns.Structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://algomaster.io/learn/lld/flyweight
 * The Flyweight Design Pattern is a structural pattern that focuses
 * on efficiently
 * sharing common parts of object state across many objects
 * to reduce memory usage and boost performance.
 *
 * useful in situations where:
 * 1. You need to create a large number of similar objects, but most of their data is shared or repeated.
 * 2. Storing all object data individually would result in high memory consumption.
 * 3. You want to separate intrinsic state (shared, reusable data) from extrinsic state (context-specific, passed in at runtime).
 *
 * Examples:
 * 1. text editors
 * 2. map applications
 * 3. game engines
 *
 *
 * The Flyweight Pattern solves this by sharing common state
 * (the intrinsic part) across all similar objects and
 * externalizing unique data (the extrinsic part).
 * It allows you to create lightweight objects
 * by caching and reusing instances instead of duplicating data.
 *
 * */
public class FlyweightDesignPattern {
    /**
     * 1. The Problem: Rendering Characters
     * Imagine you're building a rich text editor that
     * needs to render characters on screen — much like Google Docs or MS Word.
     *
     * Every character (a, b, c, ..., z, punctuation, etc.) must be displayed with formatting information such as:
     * Font family
     * Font size
     * Color
     * Style (bold, italic, etc.)
     * Position (x, y on the screen)
     * */

    static class FlyweightDesignPatternNaive {
        static class CharacterGlyph {
            private char symbol;          // e.g., 'a', 'b', etc.
            private String fontFamily;    // e.g., "Arial"
            private int fontSize;         // e.g., 12
            private String color;         // e.g., "#000000"
            private int x;                // position X
            private int y;                // position Y


            public CharacterGlyph(char symbol, String fontFamily, int fontSize, String color, int x, int y) {
                this.symbol = symbol;
                this.fontFamily = fontFamily;
                this.fontSize = fontSize;
                this.color = color;
                this.x = x;
                this.y = y;
            }

            public void draw() {
                System.out.println("Drawing '" + symbol + "' in " + fontFamily +
                        ", size " + fontSize + ", color " + color + " at (" + x + "," + y + ")");
            }
        }


        /** Why This Is a Problem?
         * 1. High Memory Usage
         * 2. Performance Bottleneck
         * 3. Poor Scalability
         * */
    }

    /**What We Really Need
     * Share the formatting data (font, size, color, etc.) among all similar characters
     * Only store what's truly unique (like position or context) for each character
     * Avoid duplicating redundant data while still rendering characters accurately
     * */

    /** Flyweight Pattern
     * The Flyweight Pattern minimizes memory usage by sharing
     * as much data as possible between similar objects.
     *
     * Components:
     * 1. Flyweight Interface - Declares a method like draw(x, y) that takes extrinsic state (position)
     * 2. ConcreteFlyweight - Implements the flyweight and stores intrinsic state( intrinsic state — the shared, repeatable properties) like font and symbol
     * 3. FlyweightFactory Caches and reuses flyweights to avoid duplication
     * 4. Client: Maintains extrinsic state and uses shared flyweights to perform operations
     * */
    static class FlyweightDesignPatternImpl{
        interface CharacterFlyweight{
            void draw(int x, int y);
        }

        static class CharacterGlyph implements  CharacterFlyweight{
            private final char symbol;
            private final String fontFamily;
            private final int fontSize;
            private final String color;

            public CharacterGlyph(char symbol, String fontFamily, int fontSize, String color) {
                this.symbol = symbol;
                this.fontFamily = fontFamily;
                this.fontSize = fontSize;
                this.color = color;
            }

            @Override
            public void draw(int x, int y) {
                System.out.println("Drawing '" + symbol + "' [Font: " + fontFamily +
                        ", Size: " + fontSize + ", Color: " + color + "] at (" + x + "," + y + ")");
            }
        }

        static class CharacterFlyweightFactory {
            private final Map<String, CharacterFlyweight> flyweightMap = new HashMap<>();

            public CharacterFlyweight getFlyweight(char symbol, String fontFamily, int fontSize, String color) {
                String key = symbol + fontFamily + fontSize + color;
                flyweightMap.putIfAbsent(key, new CharacterGlyph(symbol, fontFamily, fontSize, color));
                return flyweightMap.get(key);
            }

            public int getFlyweightCount() {
                return flyweightMap.size();
            }

        }
        static class TextEditorClient{
            private final CharacterFlyweightFactory factory = new CharacterFlyweightFactory();
            private final List<RenderedCharacter> document = new ArrayList<>();

            public void addCharacter(char c, int x, int y, String font, int size, String color){
                CharacterFlyweight glyph = factory.getFlyweight(c, font, size, color);
                document.add(new RenderedCharacter(glyph, x, y));
            }

            public void renderDocument(){
                for(RenderedCharacter rc: document){
                    rc.render();
                }
                System.out.println("Total flyweight objects used: " + factory.getFlyweightCount());
            }


            public static  class RenderedCharacter{
                private final CharacterFlyweight glyph;
                private final int x, y;

                public RenderedCharacter(CharacterFlyweight glyph, int x, int y) {
                    this.glyph = glyph;
                    this.x = x;
                    this.y = y;
                }

                public void render(){
                    glyph.draw(x, y);
                }
            }
        }

        public static class FlyweightDemo {
            public static void execute() {
                TextEditorClient editor = new TextEditorClient();

                // Render "Hello" with same style
                String word = "Hello";
                for (int i = 0; i < word.length(); i++) {
                    editor.addCharacter(word.charAt(i), 10 + i * 15, 50, "Arial", 14, "#000000");
                }

                // Render "World" with different font and color
                String word2 = "World";
                for (int i = 0; i < word2.length(); i++) {
                    editor.addCharacter(word2.charAt(i), 10 + i * 15, 100, "Times New Roman", 14, "#3333FF");
                }

                editor.renderDocument();
            }
        }
    }

    public static void main(String[] args) {
        FlyweightDesignPatternImpl.FlyweightDemo.execute();
    }

}
