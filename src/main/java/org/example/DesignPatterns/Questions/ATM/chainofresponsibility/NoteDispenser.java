package org.example.DesignPatterns.Questions.ATM.chainofresponsibility;

abstract class NoteDispenser implements DispenserChain {

    private DispenserChain next;
    private int noteValue;
    private int numNotes;


    public NoteDispenser(int noteValue, int numNotes) {
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    @Override
    public synchronized boolean canDispense(int amount) {
        if(amount < 0) return false;
        if(amount == 0) return true;

        int numToUse = Math.min(amount / noteValue, this.numNotes);
        int remainingAmount = amount - numToUse*noteValue;

        if(remainingAmount ==0)
        {
            return true;
        }

        if (this.next != null) {
            return this.next.canDispense(remainingAmount);
        }

        return false;

    }

    @Override
    public synchronized void dispense(int amount) {
        if (amount >= noteValue) {
            int numToDispense =  Math.min(amount / noteValue, this.numNotes);
            int remainingAmount = amount - (numToDispense * noteValue);

            if (numToDispense > 0) {
                System.out.println("Dispensing " + numToDispense + " x $" + noteValue + " note(s)");
                this.numNotes -= numToDispense;
            }

            if (remainingAmount > 0 && this.next != null) {
                this.next.dispense(remainingAmount);
            }

        } else if(this.next != null){
            this.next.dispense(amount);
        }
    }

    @Override
    public void setNextChain(DispenserChain nextChain) {
        this.next =  nextChain;
    }
}
