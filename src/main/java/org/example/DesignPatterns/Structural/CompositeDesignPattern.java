package org.example.DesignPatterns.Structural;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * https://algomaster.io/learn/lld/composite
 * The Composite Design Pattern is a structural pattern that
 * lets you treat individual objects
 * and compositions of objects uniformly.
 *
 * e.g. File systems, UI hierarchies, organizational charts
 * Clients can work with both single elements and
 * groups of elements using the same interface.
 *
 * 1. part-whole hierarchies.
 * 2. perform operations on both leaf nodes
 * and composite nodes in a consistent way
 * 3. You want to avoid writing special-case logic to
 * distinguish between "single" and "grouped" objects.
 * ----
 * Generalised Approach:
 * type checks to handle individual items & Grouped Items
 *  ---
 * violates the Open/Closed Principle,
 *
 * */
public class CompositeDesignPattern {
    /**
     * 1. The Problem: Modeling a File Explorer
     * The system needs to represent:
     *  1.1 Files – simple items that have a name and a size.
     *  1.2. Folders – containers that can hold files and other folders (even nested folders).
     *
     * Your goal is to support operations such as:
     *
     * getSize() – return the total size of a file or folder (which is the sum of all contents).
     * printStructure() – print the name of the item, including indentation to show hierarchy.
     * delete() – delete a file or a folder and everything inside it.
     * */

    static class CompositeDesignPatternNaive{
        static class File{
            private String name;
            private int size;

            public int getSize() {
                return size;
            }


            public void printStructure(String indent){
                System.out.println(indent + name);
            }

            public void delete(){
                System.out.println("File with name" + name + "is deleted.");
            }

        }

        static class Folder{
            private String name;
            private List<Files> contents = new ArrayList<>();

            public int getSize() {
                int total = 0;
                for (Object item : contents) {
                    if (item instanceof File) {
                        total += ((File) item).getSize();
                    } else if (item instanceof Folder) {
                        total += ((Folder) item).getSize();
                    }
                }
                return total;
            }

            public void printStructure(String indent) {
                System.out.println(indent + name + "/");
                for (Object item : contents) {
                    if (item instanceof File) {
                        ((File) item).printStructure(indent + " ");
                    } else if (item instanceof Folder) {
                        ((Folder) item).printStructure(indent + " ");
                    }
                }
            }

            public void delete() {
                for (Object item : contents) {
                    if (item instanceof File) {
                        ((File) item).delete();
                    } else if (item instanceof Folder) {
                        ((Folder) item).delete();
                    }
                }
                System.out.println("Deleting folder: " + name);
            }
        }

        /** What’s Wrong With This Approach?
         * 1. Repetitive Type Checks
         * 2. No Shared Abstraction
         * 3. Violation of Open/Closed Principle
         * 4. Lack of Recursive Elegance
         * */
    }

    /** What We Really Need
     * Introduces a common interface (e.g., FileSystemItem)
     * Allows files and folders to be treated uniformly via polymorphism.
     * Enables folders to contain a list of the same interface, supporting arbitrary nesting.
     * Supports recursive operations like delete and getSize without type checks.
     * Makes the system easy to extend — new item types can be added without modifying existing logic.
     * */

    /**
     * The Composite Design Pattern is a structural design pattern that lets you
     * treat individual objects
     * and groups of objects in a uniform way.
     *
     * 1. Component Interface (e.g., FileSystemItem)
     * 2. Leaf (e.g., File)
     * 3. Composite (e.g., Folder)
     * 4. Client (e.g., FileExplorerApp)
     * */

    static  class CompositeDesignPatternImpl{
        interface  FileSystemItem{
            public int getSize();
            public void printStructure(String indent);
            public void delete();

        }

        static  class File implements FileSystemItem{
            private String name;
            private int size;

            public File(String name, int size) {
                this.name = name;
                this.size = size;
            }

            @Override
            public int getSize() {
                return size;
            }

            @Override
            public void printStructure(String indent) {
                System.out.println(indent + "- " + name + " (" + size + " KB)");
            }

            @Override
            public void delete() {
                System.out.println("Deleting file: " + name);
            }
        }

        static  class Folder implements FileSystemItem{
            private String name;
            private List<FileSystemItem> children = new ArrayList<>();

            public Folder(String name) {
                this.name = name;
            }

            public void addItem(FileSystemItem item) {
                children.add(item);
            }

            @Override
            public int getSize() {
                int total = 0;
                for(FileSystemItem item: children){
                    total += item.getSize();
                }

                return total;
            }

            @Override
            public void printStructure(String indent) {
                System.out.println(indent + "+ " + name + "/");
                for (FileSystemItem item : children) {
                    item.printStructure(indent + "  ");
                }

            }

            @Override
            public void delete() {
                for (FileSystemItem item : children) {
                    item.delete();
                }
                System.out.println("Deleting folder: " + name);
            }
        }

        static class FileExplorerApp{
            public static void execute() {
                FileSystemItem file1 = new File("readme.txt", 5);
                FileSystemItem file2 = new File("photo.jpg", 1500);
                FileSystemItem file3 = new File("data.csv", 300);

                Folder documents = new Folder("Documents");
                documents.addItem(file1);
                documents.addItem(file3);

                Folder pictures = new Folder("Pictures");
                pictures.addItem(file2);


                Folder home = new Folder("Home");
                home.addItem(documents);
                home.addItem(pictures);

                System.out.println("---- File Structure ----");
                home.printStructure("");

                System.out.println("\nTotal Size: " + home.getSize() + " KB");

                System.out.println("\n---- Deleting All ----");
                home.delete();


            }
        }
    }

    public static void main(String[] args) {
        CompositeDesignPatternImpl.FileExplorerApp.execute();
    }

    /**
     * 1. Define the Component Interface -> FileSystemItem
     * 2. Create the Leaf Class – File
     * 3. Create the Composite Class – Folder
     * 4. Client Code
     *
     * Benefits:
     *
     * 1. Unified treatment
     * 2. Clean recursion - No instanceof no casting just delegation.
     * 3. Scalability
     * 4. Maintainability
     * 5. Extensibility
     *
     * Where it is used?
     * File systems, UI hierarchies, organizational charts
     * */
}
