package bds.threads.jointimeout;

import java.sql.Time;
import java.util.Date;

public class ExtendedThread extends Thread {
    @Override
    public void run() {
        System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - старт потока");
        super.run();
        for(int i=0; i<1_000; i++)
             try {
                 sleep(10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

        System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - завершение потока");
    }
}
