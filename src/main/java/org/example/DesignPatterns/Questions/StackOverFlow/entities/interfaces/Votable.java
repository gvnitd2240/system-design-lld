package org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.User;
import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;

public interface Votable {
    public Votable vote(User user, VoteType voteType);

    public int getVoteCount();
}
