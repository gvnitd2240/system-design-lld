package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.Example;

public class Impl {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

//        ProducerTask producerTask = new ProducerTask(sharedResource);
//        ConsumeTask consumeTask = new ConsumeTask(sharedResource);

        Thread t1 = new Thread(()->{
            System.out.println("Producer Thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });

        Thread t2 = new Thread(()->{
            System.out.println("Consuming Thread "+ Thread.currentThread().getName());
            sharedResource.removeItem();
        });

        t1.start();
        t2.start();

        System.out.println("Main Thread ended.");


    }
}
