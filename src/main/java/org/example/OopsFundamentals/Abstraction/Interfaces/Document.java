package org.example.OopsFundamentals.Abstraction.Interfaces;

import javax.print.Doc;

public class Document {
    private String content;

    Document(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }
}
