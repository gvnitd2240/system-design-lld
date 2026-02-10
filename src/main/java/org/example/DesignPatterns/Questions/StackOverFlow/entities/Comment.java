package org.example.DesignPatterns.Questions.StackOverFlow.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Comment {
    private UUID id;
    private String content;
    private User author;
    private Date creationDate;

    public Comment(String content, User author) {
        this.content = content;
        this.author = author;
        this.id = UUID.randomUUID();
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }
}
