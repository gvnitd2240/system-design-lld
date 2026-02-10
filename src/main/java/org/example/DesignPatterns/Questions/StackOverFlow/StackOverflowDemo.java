package org.example.DesignPatterns.Questions.StackOverFlow;


import org.example.DesignPatterns.Questions.StackOverFlow.entities.Answer;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.Question;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.Tags;
import org.example.DesignPatterns.Questions.StackOverFlow.entities.User;
import org.example.DesignPatterns.Questions.StackOverFlow.enums.VoteType;
import org.example.DesignPatterns.Questions.StackOverFlow.search.SearchByUserStretegy;
import org.example.DesignPatterns.Questions.StackOverFlow.search.SearchStretegy;
import org.example.DesignPatterns.Questions.StackOverFlow.search.TagSearchStrategy;

import java.util.List;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverFlowSystem service = StackOverFlowSystem.getInstance();

        // 1. Create Users
        User alice = service.createUser("Alice", "alice@gmail.com");
        User bob = service.createUser("Bob", "bob@gmail.com");
        User charlie = service.createUser("Charlie", "charlie@gmail.com");

        // 2. Alice posts a question
        System.out.println("--- Alice posts a question ---");
        Tags javaTag = new Tags("java");
        Tags designPatternsTag = new Tags("design-patterns");
        List<Tags> tags = List.of(javaTag, designPatternsTag);
        Question question = service.postQuestion(alice.getId(), "How to implement Observer Pattern?", "Details about Observer Pattern...", tags);
        printReputations(alice, bob, charlie);

        // 3. Bob and Charlie post answers
        System.out.println("\n--- Bob and Charlie post answers ---");
        Answer bobAnswer = service.postAnswer(bob.getId(), question.getId(), "You can use the java.util.Observer interface.");
        Answer charlieAnswer = service.postAnswer(charlie.getId(), question.getId(), "A better way is to create your own Observer interface.");
        printReputations(alice, bob, charlie);

        // 4. Voting happens
        System.out.println("\n--- Voting Occurs ---");
        service.vote(alice.getId(), question.getId(), VoteType.UPVOTE); // Alice upvotes her own question
        service.vote(bob.getId(), charlieAnswer.getId(), VoteType.UPVOTE); // Bob upvotes Charlie's answer
        service.vote(alice.getId(), bobAnswer.getId(), VoteType.DOWNVOTE); // Alice downvotes Bob's answer
        printReputations(alice, bob, charlie);

        // 5. Alice accepts Charlie's answer
        System.out.println("\n--- Alice accepts Charlie's answer ---");
        service.acceptAnswer(question.getId(), charlieAnswer.getId());
        printReputations(alice, bob, charlie);

        // 6. Search for questions
        System.out.println("\n--- (C) Combined Search: Questions by 'Alice' with tag 'java' ---");
        List<SearchStretegy> filtersC = List.of(
                new SearchByUserStretegy(alice),
                new TagSearchStrategy(javaTag)
        );

        service.setSearchStretegies(filtersC);
        List<Question> searchResults = service.searchQuestions();
        searchResults.forEach(q -> System.out.println("  - Found: " + q.getTitle()));
    }

    private static void printReputations(User... users) {
        System.out.println("--- Current Reputations ---");
        for(User user : users) {
            System.out.printf("%s: %d\n", user.getName(), user.getReputation());
        }
    }
}
