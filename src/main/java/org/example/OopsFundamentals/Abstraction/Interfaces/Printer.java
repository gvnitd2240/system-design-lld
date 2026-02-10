package org.example.OopsFundamentals.Abstraction.Interfaces;

public class Printer implements Printable{
    @Override
    public void print(Document document) {
        System.out.println(document.getContent());
    }
}
