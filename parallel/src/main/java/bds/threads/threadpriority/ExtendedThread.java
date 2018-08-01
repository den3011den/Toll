package bds.threads.threadpriority;

import java.util.Date;

public class ExtendedThread extends Thread {

    public ExtendedThread(String threadName, int priority) {
        this.setName(threadName);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        for (int i=0; i<5 ; i++)
            System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - занимает устройство");
    }
}
