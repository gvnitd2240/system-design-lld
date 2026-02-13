package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl;

/**
 * It help to make sure that only 1 thread goes inside the particular section of code
 * (a synchronized block or method)
 * A Monitor lock is acquired on a object, and only one thread will go in critical part of code.
 * */
public class MonitorLockDemo {
    public static void main(String[] args) {
        MonitorLock monitorLock = new MonitorLock();
        MonitorLockRunnable lockRunnable = new MonitorLockRunnable(monitorLock);

        Thread t2 = new Thread(monitorLock::task2);
        Thread t3 = new Thread(monitorLock::task3);
        Thread t1 = new Thread(lockRunnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
