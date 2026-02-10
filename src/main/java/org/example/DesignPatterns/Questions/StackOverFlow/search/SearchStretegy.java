package org.example.DesignPatterns.Questions.StackOverFlow.search;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.Question;

import java.util.List;

public interface SearchStretegy {
    public List<Question> search(List<Question> questions);
}
