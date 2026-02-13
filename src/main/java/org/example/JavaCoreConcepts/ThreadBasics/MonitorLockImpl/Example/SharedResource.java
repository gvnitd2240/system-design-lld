package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.Example;

public class SharedResource {
    boolean isAvailable  = false;

    public synchronized void addItem(){
        isAvailable = true;
        System.out.println("Item added by" + Thread.currentThread().getName() + " and invoking all threads waiting.");
        notifyAll(); // will notify all the threads which are in waiting state.
    }

    public synchronized void removeItem(){
        System.out.println("Consume Item Invoked by " + Thread.currentThread().getName());

        // to avoid spurious wake up due to system noise.
        while(!isAvailable){
            try{
                System.out.println("Thread " + Thread.currentThread().getName() + "is waiting now.");
                wait(); // it will release all the monitor lock.
            } catch (Exception e){

            }
        }

        System.out.println("Item consumed by "+ Thread.currentThread().getName());
        isAvailable = false;
    }

}
