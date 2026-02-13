package org.example.DesignPatterns.Questions.ATM.state;

import org.example.DesignPatterns.Questions.ATM.ATMSystem;
import org.example.DesignPatterns.Questions.ATM.enums.OperationType;

public interface ATMState {
    void insertCard(ATMSystem atmSystem, String cardNumber);
    void enterPin(ATMSystem atmSystem, String pin);
    void selectOperation(ATMSystem atmSystem, OperationType op, int args);
    void ejectCard(ATMSystem atmSystem);
}
