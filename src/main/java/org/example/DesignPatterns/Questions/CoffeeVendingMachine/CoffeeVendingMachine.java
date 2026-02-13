package org.example.DesignPatterns.Questions.CoffeeVendingMachine;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.CaramelSyrupDecorator;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.ExtraSugarDecorator;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.CoffeeType;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.ToppingType;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.factory.CoffeeFactory;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.state.CoffeeVendingState;

import java.util.List;

public class CoffeeVendingMachine {
    private static CoffeeVendingMachine instance;
    private CoffeeVendingState vendingState;
    private Coffee selectedCoffee;
    private int moneyInserted;


    public static CoffeeVendingMachine getInstance(){
        if(instance == null){
            synchronized (CoffeeVendingMachine.class){
                if(instance == null){
                    instance = new CoffeeVendingMachine();
                }
            }
        }

        return instance;
    }

    public void setVendingState(CoffeeVendingState vendingState){
        this.vendingState = vendingState;
    }

    public CoffeeVendingState getVendingState(){
        return vendingState;
    }

    public Coffee getSelectedCoffee(){
        return selectedCoffee;
    }

    public void reset(){
        this.selectedCoffee = null;
        this.moneyInserted = 0;
    }

    public int getMoneyInserted() {
        return moneyInserted;
    }

    public void setMoneyInserted(int moneyInserted) {
        this.moneyInserted = moneyInserted;
    }

    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    public void cancel(){
        vendingState.cancel(this);
    }

    public void selectCoffee(CoffeeType type, List<ToppingType> toppings){
        Coffee coffee = CoffeeFactory.createCoffee(type);

        for (ToppingType topping : toppings) {
            switch (topping) {
                case EXTRA_SUGAR:
                    coffee = new ExtraSugarDecorator(coffee);
                    break;
                case CARAMEL_SYRUP:
                    coffee = new CaramelSyrupDecorator(coffee);
                    break;
            }
        }
        // Let the state handle the rest
        this.vendingState.selectCoffee(this, coffee);
    }

    public void insertMoney(int money){

        vendingState.insertMoney(this, money);
    }

    public void dispenseCoffee(){
        vendingState.dispense(this);
    }
}
