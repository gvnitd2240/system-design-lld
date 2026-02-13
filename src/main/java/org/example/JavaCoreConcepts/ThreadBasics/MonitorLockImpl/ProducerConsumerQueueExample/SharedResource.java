package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl.ProducerConsumerQueueExample;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> queue;
    private int bufferSize;

    public SharedResource( int bufferSize) {
        this.queue = new LinkedList<>();
        this.bufferSize = bufferSize;
    }


    public synchronized void produce(int i){
        System.out.println("Produced by " + Thread.currentThread().getName());
        while(queue.size() == bufferSize){
            try{
                System.out.println("Thread" + Thread.currentThread().getName() + " is waiting as the queue is full.");
                wait();
            } catch (Exception e){

            }

        }

        queue.add(i);
        System.out.println("Item " + i + " added by " + Thread.currentThread().getName());
        notifyAll();

    }

    public synchronized void consume(){
        System.out.println("consume by " + Thread.currentThread().getName());

        while(queue.isEmpty()){
            try{
                System.out.println("Thread" + Thread.currentThread().getName() + " is waiting as the queue is empty.");
                wait();
            } catch (Exception e){

            }
        }

        System.out.println("Item" + queue.poll() + "is consumed by Thread "+  Thread.currentThread().getName());
        notifyAll();
    }
}
