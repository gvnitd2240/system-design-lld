package org.example.DesignPatterns.Questions.StackOverFlow.entities;

import java.util.UUID;

public class Tags {
    private UUID id;
    private String name;

    public Tags(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


