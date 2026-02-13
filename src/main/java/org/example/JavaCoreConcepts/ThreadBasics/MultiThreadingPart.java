package org.example.JavaCoreConcepts.ThreadBasics;

/**
 * 1. https://notebook.zohopublic.in/public/notes/74tdo52a4834de5554f09bc9ec3f11572cd11
 * 2. https://notebook.zohopublic.in/public/notes/74tdo47dcfcf05e644cf192c411a6d78ec1dc
 * */
public class MultiThreadingPart {

    /**
     * Thread can be created in two ways:
     * 1. Thread class
     * 2. Runnable Interface
     *
     * Why we have two ways to create thread?
     *
     * 1. class can be implemented by more than 1 interface
     * 2. a class can be extended by only 1 class.
     *
     *
     * Thread Life Cycles:
     *
     * 1. New : Thread has been created but not started. It is just an object in memory.
     * 2. Runnable: Thread is ready to run. Waiting for CPU time.
     * 3. Running: when thread start executing its code.
     * 4. Blocked: Different scenarios where thread go to blocked state:
     *      1. I/O like reading from a file or database.
     *      2. Lock acquired: if thread want to acquire a lock on a resource which is locked by other thread, it has to wait.
     * Release all the monitors locks.
     * 5. Waiting: Thread goes into this state when we call wait() method, makes it no runnable. It goes back to runnable once we will notify() or notifyAll() method. Releases all monitor locks.
     * 6. Timed Waiting: Thread wait for specific time period and comes back to runnable after specific conditions are met. like sleep() and jon().
     * 7. Terminated: Life of thread is completed, it can not be started back again.
     * */

    public static class MultiThreadingPartRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("Code Executed by " + Thread.currentThread().getName());
        }
    }

    public static class MultiThreadingPartThread extends Thread{
        @Override
        public void run() {
            System.out.println("Code Executed by MultiThreadingPartThread " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println("Going inside the main thread " + Thread.currentThread().getName());
        Runnable runnable = new MultiThreadingPartRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Finishing Main Method" + Thread.currentThread().getName());

        Thread thread1 = new MultiThreadingPartThread();
        thread1.start();

    }




}
