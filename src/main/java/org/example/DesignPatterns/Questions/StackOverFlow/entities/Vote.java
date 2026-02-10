package org.example.DesignPatterns.Questions.StackOverFlow.entities;

import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;

public class Vote {
    private User user;
    private VoteType voteType;

    public Vote(User user, VoteType voteType) {
        this.user = user;
        this.voteType = voteType;
    }

    public User getUser() {
        return user;
    }

    public VoteType getVoteType() {
        return voteType;
    }
}
