package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.Example;

public class ProducerTask implements Runnable{
    private SharedResource sharedResource;

    public ProducerTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        System.out.println("Producer Thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sharedResource.addItem();
    }
}
