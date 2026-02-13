package org.example.DesignPatterns.Questions.ATM;

import org.example.DesignPatterns.Questions.ATM.chainofresponsibility.DispenserChain;
import org.example.DesignPatterns.Questions.ATM.chainofresponsibility.Note100Dispenser;
import org.example.DesignPatterns.Questions.ATM.chainofresponsibility.Note20Dispenser;
import org.example.DesignPatterns.Questions.ATM.chainofresponsibility.Note50Dispenser;
import org.example.DesignPatterns.Questions.ATM.entities.BankService;
import org.example.DesignPatterns.Questions.ATM.entities.Card;
import org.example.DesignPatterns.Questions.ATM.entities.CashDispenser;
import org.example.DesignPatterns.Questions.ATM.enums.OperationType;
import org.example.DesignPatterns.Questions.ATM.state.ATMState;
import org.example.DesignPatterns.Questions.ATM.state.IdleState;

import javax.print.DocFlavor;
import java.lang.ref.PhantomReference;

public class ATMSystem {
    /**
     * State
     * currentCard;
     * Bank Service;
     * Cash Dispenser
     * instance
     *
     * getcard;
     * getInstance
     * setState;
     * setCurrentCard;
     * setbankservice;
     *
     * insertCard
     * selectOperation -> WITHDRAW, DEPOSIT, CHECK_BALANCE;
     * enter Pin
     * pin needs to be validated -> setState(authenticatedState)/
     * withdraw cash
     * deposit
     * check balance;
     * depenseCard;
     * */

    /**
     * Account: number, balance, Card; List<Transactions>, Bank bankName;
     * Card: number, pin
     * Transaction: I: withdraw, deposit, check balance -> WithDrawl Class, DepositClass
     * BankingService -> Banking accounts, manages accounts and their transactions;
     * CashDispenser -> dispense the cash, -> depending upon the denomination it has.
     * Atm State: IdleState, HasCardState, Authenticated
     *
     * */

    private static ATMSystem instance;
    private BankService bankService;
    private ATMState atmState;
    private CashDispenser cashDispenser;
    private Card currentCard;

    private ATMSystem() {
        this.atmState = new IdleState();
        this.currentCard = null;
        this.bankService = new BankService();

        DispenserChain c1 = new Note100Dispenser(10); // 10 x $100 notes
        DispenserChain c2 = new Note50Dispenser(20); // 20 x $50 notes
        DispenserChain c3 = new Note20Dispenser(30); // 30 x $20 notes

        c1.setNextChain(c2);
        c3.setNextChain(c3);

        this.cashDispenser = new CashDispenser(c1);

    }

    public void changeState(ATMState newState) { this.atmState = newState; }

    public void insertCard(String cardNumber) {
        atmState.insertCard(this, cardNumber);
    }
    public void enterPin(String pin) {
        atmState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int args) { atmState.selectOperation(this, op, args); }

    public Card getCard(String cardNumber) {
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin) {
        return bankService.authenticate(currentCard, pin);
    }

    public void checkBalance() {
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispense(amount)) {
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        bankService.withdrawMoney(currentCard, amount);

        try {
            cashDispenser.dispense(amount);
        } catch (Exception e) {
            bankService.depositMoney(currentCard, amount); // Deposit back if dispensing fails
        }
    }

    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }


    public static ATMSystem getInstance(){
        if(instance == null){
            synchronized (ATMSystem.class){
                if(instance == null){
                    instance = new ATMSystem();
                }
            }
        }
        return instance;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }
}
