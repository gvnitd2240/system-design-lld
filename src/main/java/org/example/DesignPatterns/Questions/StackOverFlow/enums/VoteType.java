package org.example.DesignPatterns.Questions.StackOverFlow.enums;

public enum VoteType {

    UPVOTE(1),
    DOWNVOTE(-1);

    private final int value;

    VoteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static VoteType type(int value) {
        for (VoteType voteType : VoteType.values()) {
            if (voteType.value == value) {
                return voteType;
            }
        }
        throw new IllegalArgumentException("Invalid vote value: " + value);
    }
}