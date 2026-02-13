package org.example.JavaCoreConcepts.ThreadBasics.MonitorLockImpl;

public class MonitorLockRunnable implements Runnable{
    private MonitorLock monitorLock;

    MonitorLockRunnable(MonitorLock monitorLock){
        this.monitorLock = monitorLock;
    }

    @Override
    public void run() {
        monitorLock.task1();
    }
}
