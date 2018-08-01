package bds.threads.threadpriority;

import bds.threads.threadpriority.ExtendedThread;

import java.util.Date;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {

        ExtendedThread extendedThread1 = new ExtendedThread("Max_Thread", MAX_PRIORITY);
        ExtendedThread extendedThread2 = new ExtendedThread("Norm_Thread", NORM_PRIORITY);
        ExtendedThread extendedThread3 = new ExtendedThread("Min_Thread", MIN_PRIORITY);

        extendedThread3.start();
        extendedThread2.start();
        extendedThread1.start();
    }

}
