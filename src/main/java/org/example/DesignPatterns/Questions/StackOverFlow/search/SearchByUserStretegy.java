package org.example.DesignPatterns.Questions.StackOverFlow.search;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.Question;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.User;

import java.util.List;

public class SearchByUserStretegy implements SearchStretegy{
    private final User user;

    public SearchByUserStretegy(User user) {
        this.user = user;
    }


    @Override
    public List<Question> search(List<Question> questions) {
        return questions.stream().filter((it) -> it.getAuthor().getId() == user.getId()).toList();
    }
}
