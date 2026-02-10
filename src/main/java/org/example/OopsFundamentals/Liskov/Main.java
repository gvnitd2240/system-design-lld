package org.example.OopsFundamentals.Liskov;

import javax.print.Doc;

public class Main {
    public interface  Editable{
        public void save(String newData);
    }

    public interface  Document{
        public void open();
        public String getData();
    }

    public class EditableDocument implements  Document, Editable{
        String data;

        EditableDocument(String data){
            this.data= data;
        }

        @Override
        public void save(String newData) {
            System.out.println("Saving Editable document");
        }

        @Override
        public void open() {
            System.out.println("Opening Editable document");
        }

        @Override
        public String getData() {
            return this.data;
        }
    }
    public class ReadOnlyDocument implements Document{
        String data;
        ReadOnlyDocument(String data){
            this.data = data;
        }

        @Override
        public void open() {
            System.out.println("Opening ReadOnlyDocument");
        }

        @Override
        public String getData() {
            return this.data;
        }
    }

    class DocumentProcessor{
        public void processAndSave(Editable doc, String newData){

            System.out.println("Document Edited.");

        }

        public void processAndSaveEditable(Editable doc, String newData){
            System.out.println("Edit Document is allowed.");
        }

        public void processAndSaveReadonly(Document doc, String newData){
            System.out.println("ReadOnlyDocument Document is not allowed to change.");
        }
    }

    public void main(String[] args) {
        Document readonlyDocument = new ReadOnlyDocument("Readonly");
        DocumentProcessor documentProcessor = new DocumentProcessor();
//        documentProcessor.processAndSave(readonlyDocument, "bjhbdjhvbdj"); // compile time error

        Editable editableDocument = new EditableDocument("Readonly");
        documentProcessor.processAndSave(editableDocument, "bjhbdjhvbdj");

        documentProcessor.processAndSaveReadonly(readonlyDocument, "nkdfnjkdfnv");
        documentProcessor.processAndSaveEditable(editableDocument, "nkdfnjkdfnv");

    }
}
