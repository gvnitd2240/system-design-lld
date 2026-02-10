package org.example.DesignPatterns.Questions.StackOverFlow.entities;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Commentable;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Votable;
import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer implements Commentable, Votable {
    final private UUID id;
    final private String content;
    final private User author;
    final private Question question;
    private boolean isAccepted;
    final private Date creationDate;
    final private List<Comment> comments;
    final private List<Vote> votes;

    public Answer(String content, User author, Question question){
        this.author = author;
        this.id = UUID.randomUUID();
        this.content =content;
        this.question = question;
        this.isAccepted = false;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.creationDate = Date.valueOf(LocalDate.now());
    }

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return this.comments;
    }

    @Override
    public Votable vote(User user, VoteType voteType) {
        Vote vote = new Vote(user, voteType);
        this.votes.add(vote);
        return this;
    }

    @Override
    public int getVoteCount() {
        return this.votes.size();
    }

    public Question getQuestion() {
        return question;
    }

    public void acceptAnswer(){
        isAccepted = true;
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

    public boolean isAccepted() {
        return isAccepted;
    }
}

//id, content, author, associated question, comments, votes and creation date
