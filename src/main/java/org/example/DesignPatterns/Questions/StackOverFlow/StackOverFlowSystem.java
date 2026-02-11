package org.example.DesignPatterns.Questions.StackOverFlow;

import org.example.DesignPatterns.Questions.StackOverFlow.entities.*;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Commentable;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.interfaces.Votable;
import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;
import org.example.DesignPatterns.Questions.StackOverFlow.search.SearchStretegy;

import java.util.*;

public class StackOverFlowSystem {
    private static StackOverFlowSystem instance;
    private Map<UUID, User> users;
    private  Map<UUID, Question> questions;
    private Map<UUID, Answer> answers;
    private Map<UUID, Tags> tags;

    private Map<UUID, Votable> votableMap;

    private List<SearchStretegy> searchStretegies;

    private StackOverFlowSystem(){
        this.users = new HashMap<>();
        this.questions = new HashMap<>();
        this.answers = new HashMap<>();
        this.tags = new HashMap<>();
        this.votableMap =  new HashMap<>();
    }

    public void setSearchStretegies(List<SearchStretegy> searchStretegies) {
        this.searchStretegies = searchStretegies;
    }

    public User createUser(String name, String email){
        User user = new User(email, name);
        this.users.put(user.getId(), user);
        return user;
    }

    public Question postQuestion(UUID userId, String content, String title, List<Tags> questionTags){
        if(!users.containsKey(userId)) {
            throw new IllegalArgumentException("No user found with this id.");
        }

        Question question = new Question(
                title, content, users.get(userId), questionTags
        );

        this.questions.put(question.getId(), question);
        this.votableMap.put(question.getId(), question);

        return question;
    }

    public Answer postAnswer(UUID userId, UUID questionId, String content){
        if(!users.containsKey(userId)){
            throw new IllegalArgumentException("No user found with this id." + userId);
        }

        if(!questions.containsKey(questionId)){
            throw new IllegalArgumentException("No question found with this id." + questionId);
        }

        Question question =questions.get(questionId);

        Answer answer = new Answer(
                content,
                users.get(userId),
                question
        );

        answers.put(answer.getId(), answer);
        this.votableMap.put(answer.getId(), question);
        question.addAnswer(answer);

        return answer;
    }
    public Comment addComment(UUID userId, Commentable commentable, String content){
        if(!users.containsKey(userId)){
            throw new IllegalArgumentException("No user found with this id." + userId);
        }
        Comment comment = new Comment(content, users.get(userId));
        commentable.addComment(comment);
        return comment;
    }

    public void vote(UUID userId, UUID votableId, VoteType voteType){
        if(!users.containsKey(userId)){
            throw new IllegalArgumentException("No user found with this id." + userId);
        }
        if(!votableMap.containsKey(votableId)){
            throw new IllegalArgumentException("No Votable found with this id." + userId);
        }
        this.votableMap.get(votableId).vote(this.users.get(userId), voteType);
    }

    public void acceptAnswer(UUID questionId, UUID answerId){
        if(!this.questions.containsKey(questionId)){
            throw new IllegalArgumentException("No question found with this id." + questionId);
        }
        if(!this.answers.containsKey(answerId)){
            throw new IllegalArgumentException("No answer found with this id." + answerId);
        }

        this.questions.get(questionId).acceptAnswer(answers.get(answerId));
        this.answers.get(answerId).acceptAnswer();
    }

    public List<Question> searchQuestions(){
        List<Question> results = new ArrayList<>(questions.values());

        for(SearchStretegy searchStretegy: searchStretegies){
            results = searchStretegy.search(results);
        }

        return results;
    }

    public static StackOverFlowSystem getInstance(){
        if(instance == null){
            synchronized (StackOverFlowSystem.class){
                if(instance == null){
                    instance = new StackOverFlowSystem();
                }
            }
        }
        return instance;
    }
}
