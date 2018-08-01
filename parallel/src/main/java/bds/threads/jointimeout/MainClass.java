package bds.threads.jointimeout;

import java.util.Date;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {

        ExtendedThread extendedThread1 = new ExtendedThread();
        ExtendedThread extendedThread2 = new ExtendedThread();

        extendedThread1.start();

        System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - ждём завершения потока");

        extendedThread1.join(10);

        System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - перестали ждать завершения потока");

        extendedThread2.start();

    }

}
