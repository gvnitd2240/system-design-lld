package org.example.OopsFundamentals.Abstraction.Interfaces;

public class Main {
    public static void main(String[] args) {
        Document document = new Document("Heyy its me. Your loving friend. Vivek!!!");
        Printable printer = new Printer();
        printer.print(document);
    }
}
