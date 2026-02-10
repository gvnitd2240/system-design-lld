package org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.Comment;

import java.util.List;

public interface Commentable {
    void addComment(Comment comment);
    List<Comment> getComments();
}
