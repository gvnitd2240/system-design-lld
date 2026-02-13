package org.example.DesignPatterns.Questions.ATM.entities;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final String number;
    private double balance;
    private Map<String, Card> cards;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
        this.cards = new HashMap<>();;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double  amount){
        balance += amount;
    }

    public synchronized boolean wihdraw(double  amount){
        if(amount>balance){
            throw new IllegalArgumentException("No enough balance.");
        }
        balance -= amount;
        return true;
    }
}
