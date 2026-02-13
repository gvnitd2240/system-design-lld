package org.example.DesignPatterns.Questions.ATM.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private Map<String, Account> accountMap = new ConcurrentHashMap<>();;
    private Map<String, Card> cardMap =  new ConcurrentHashMap<>();;
    private Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();;

    public BankService() {
        // Create sample accounts and cards

        Account account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", "1234");
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", "4321");
        linkCardToAccount(card2, account2);
    }

    public Account createAccount(String number, double amount){
        Account account = new Account(number, amount);
        accountMap.put(number, account);
        return account;
    }

    public Card createCard(String number, String pin){
        Card card = new Card(number, pin);
        cardMap.put(number, card);
        return card;
    }

    public void linkCardToAccount(Card card, Account account){
        account.getCards().put(card.getNumber(), card);
        cardAccountMap.put(card, account);
    }

    public boolean authenticate(Card card, String pin)
    {
        return card.getPin().equals(pin);
    }

    public Card getCard(String number){
        return cardMap.getOrDefault(number, null);
    }

    public double getBalance(Card card) {
        return cardAccountMap.get(card).getBalance();
    }

    public void withdrawMoney(Card card, double amount) {
        cardAccountMap.get(card).wihdraw(amount);
    }

    public void depositMoney(Card card, double amount) {
        cardAccountMap.get(card).deposit(amount);
    }
}
