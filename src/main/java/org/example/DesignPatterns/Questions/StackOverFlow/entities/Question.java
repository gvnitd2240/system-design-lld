package org.example.DesignPatterns.Questions.StackOverFlow.entities;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Commentable;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Votable;
import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question implements Votable, Commentable {
    private UUID id;
    private String title;
    private String content;
    private User author;
    private List<Answer> answers;
    private List<Comment> comments;
    private List<Tags> tags;
    private Date creationDate;
    private Answer acceptedAnswer;

    private List<Vote> votes;

    public Question(String title, String content, User author, List<Tags> tags) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.author = author;
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tags = tags;
        this.votes = new ArrayList<>();
        this.creationDate = Date.valueOf(LocalDate.now());
        this.acceptedAnswer = null;
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

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public void acceptAnswer(Answer answer){
        this.acceptedAnswer = answer;
    }

    public int getVoteCount(){
        return this.votes.size();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", answers=" + answers +
                ", comments=" + comments +
                ", tags=" + tags +
                ", creationDate=" + creationDate +
                ", acceptedAnswer=" + acceptedAnswer +
                ", votes=" + votes +
                '}';
    }
}
