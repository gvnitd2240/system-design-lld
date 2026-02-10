package org.example.DesignPatterns.Behavior;

import java.util.Stack;

/**
 * https://algomaster.io/learn/lld/memento
 * The Memento Design Pattern is a behavioral design pattern that
 * lets you capture and store an object’s internal state so it
 * can be restored later, without violating encapsulation.
 *
 * It’s particularly useful in situations where:
 * 1. You need to implement undo/redo functionality.
 * 2. You want to support checkpointing or versioning of an object’s state.
 * 3. You want to separate the concerns of state storage from state management logic.
 * */
public class MementoDesignPattern {
    /**
     * 1. The Problem: Implementing Undo in a Text Editor
     * Imagine you’re building a simple text editor. The editor supports basic operations like:
     * 1. type(String text) – appends text to the current document
     * 2. getContent() – returns the current document text
     * 3. undo() – reverts to the previous version of the content
     * */

    static class MementoDesignPatternNaive{
        static class TextEditorNaive {
            private String content = "";
            private Stack<String> snapshots = new Stack<>();

            public void type(String newText) {
                content += newText;
                snapshots.add(content);
            }
            public void undo() {
                snapshots.pop();
                content = snapshots.peek();
            }

            public String getContent() {
                return content;
            }

            /**
             * What’s Wrong with This Design?
             * 1. Encapsulation is Broken
             * 2. Manual Snapshot Management
             * 3. Not Scalable
             * */
        }
    }

    /**
     * What We Really Need?
     * The TextEditor to expose a safe way to capture and restore its state
     * The client to manage these states (snapshots) without knowing or touching internal fields
     * Undo/redo to be implemented in a way that is scalable, maintainable, and encapsulated
     * */

    /**
     * Memento Pattern: The Memento Design Pattern allows an object
     * to save and restore its state without exposing its
     * internal structure. It achieves
     * this by encapsulating the state in a special object called a Memento.
     *
     * 1. Originator (e.g., TextEditor) -
     *      Creating a memento that captures its current state
     *      Restoring its state from a memento when needed (e.g., during an undo operation)
     * 2. Memento
     *      A passive object that holds the snapshot of the Originator’s state at a given point in time.
     *      It does not expose any methods to modify its state.
     *      Only the Originator can access the internal state inside the memento.
     * 3. ConcreteMemento (Optional)
     *      In more complex systems, the Memento might be defined via an interface or abstract class, and a ConcreteMemento provides the actual implementation.
     *      This allows for future extensibility or multiple types of mementos.
     *      In many simple use cases (like ours), the Memento itself acts as the concrete implementation.
     * 4. Caretaker
     *      The Caretaker is responsible for storing, managing, and restoring mementos.
     *      It never examines or modifies the content of a memento, it just treats it as a black box.
     * */

    static class MementoDesignPatternImpl{
        static class TextEditorMemento{
            private final String state;
            TextEditorMemento(String state) {
                this.state = state;
            }

            public String getState() {
                return state;
            }
        }

        static class TextEditor{
            private String content = "";

            public void type(String newText) {
                content += newText;
                System.out.println("Typed: " + newText);
            }

            public String getContent() {
                return content;
            }

            public TextEditorMemento save() {
                System.out.println("Saving state: \"" + content + "\"");
                return new TextEditorMemento(content);
            }

            public void restore(TextEditorMemento memento) {
                content = memento.getState();
                System.out.println("Restored state to: \"" + content + "\"");
            }

        }

        static  class TextEditorUndoManager {
            private final Stack<TextEditorMemento> history = new Stack<>();

            public void save(TextEditor editor) {
                history.push(editor.save());
            }

            public void undo(TextEditor editor) {
                if (!history.isEmpty()) {
                    editor.restore(history.pop());
                } else {
                    System.out.println("Nothing to undo.");
                }
            }
        }
    }

    public static void main(String[] args) {
        MementoDesignPatternNaive.TextEditorNaive textEditorNaive = new MementoDesignPatternNaive.TextEditorNaive();
        textEditorNaive.type("hello");
        textEditorNaive.type(" world");
        System.out.println("Current Content: " + textEditorNaive.getContent()); // Hello World
        textEditorNaive.undo();
        System.out.println("After Undo: " + textEditorNaive.getContent()); // Hello
        textEditorNaive.type(" Viovek");
        textEditorNaive.type(" garg");
        textEditorNaive.undo();
        System.out.println("After Undo: " + textEditorNaive.getContent()); // Hello


        MementoDesignPatternImpl.TextEditor editor = new MementoDesignPatternImpl.TextEditor();
        MementoDesignPatternImpl.TextEditorUndoManager undoManager = new MementoDesignPatternImpl.TextEditorUndoManager();
        editor.type("Hello");
        undoManager.save(editor);


        editor.type(" World");
        undoManager.save(editor); // save state: Hello World

        editor.type("!");
        System.out.println("Current Content: " + editor.getContent()); // Hello World!


        System.out.println("\n--- Undo 1 ---");
        undoManager.undo(editor); // Back to: Hello World

        System.out.println("\n--- Undo 2 ---");
        undoManager.undo(editor); // Back to: Hello

        System.out.println("\n--- Undo 3 ---");
        undoManager.undo(editor); // Nothing left to undo
    }
}
