package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.Example;

public class ConsumeTask implements Runnable{
    private SharedResource sharedResource;

    public ConsumeTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }


    @Override
    public void run() {
        System.out.println("Consuming Thread "+ Thread.currentThread().getName());
        sharedResource.removeItem();
    }
}
