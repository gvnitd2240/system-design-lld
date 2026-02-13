package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.ProducerConsumerQueueExample;


public class Demo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(10);

        Thread t1 = new Thread(()->{
            for(int i = 0;i<15;i++){
                sharedResource.produce(i);
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0;i<15;i++){
                sharedResource.consume();
            }
        });

        t1.start();
        t2.start();


    }
}
