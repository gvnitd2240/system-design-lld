package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl;

public class MonitorLock {
    public synchronized void task1(){
        try{
            System.out.println("Inside Task1");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void task2(){
        System.out.println("Inside Task2, before synchronized");

        synchronized (this){
            System.out.println("Inside Task2, inside synchronized");
        }
    }

    public void task3() {
        System.out.println("Inside Task3, before synchronized");
    }

    public static void main(String[] args) {
        MonitorLock monitorLock = new MonitorLock();

        Thread t1 = new Thread(monitorLock::task1);
        Thread t2 = new Thread(monitorLock::task2);
        Thread t3 = new Thread(monitorLock::task3);

        t1.start();
        t2.start();
        t3.start();

        /**
         * Output::
         * Inside Task3, before synchronized
         * Inside Task1
         * Inside Task2, before synchronized
         * Inside Task2, inside synchronized
         * */
    }
}
