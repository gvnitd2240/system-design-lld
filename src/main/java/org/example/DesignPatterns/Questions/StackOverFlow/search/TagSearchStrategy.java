package org.example.DesignPatterns.Questions.StackOverFlow.search;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.Question;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.Tags;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearchStrategy implements SearchStretegy{
    private final Tags tags;

    public TagSearchStrategy(Tags tags) {
        this.tags = tags;
    }

    @Override
    public List<Question> search(List<Question> questions) {
        return questions.stream().filter((it) -> it.getTags().stream().anyMatch(t -> t.getName().equalsIgnoreCase(tags.getName()))).collect(Collectors.toList());
    }
}
