package org.example.DesignPatterns.Questions.StackOverFlow.entities;


import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String name;
    private int reputation;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getReputation(){
        return this.reputation;
    }
    void updateReputation(int reputation){
        this.reputation = reputation;
    }
}
