package bds.threads.yeld;

import java.util.Date;

public class ExtendedThread extends Thread {
    @Override
    public void run() {
        for (int i=0; i<5 ; i++)
            System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - занимает устройство");
    }
}
