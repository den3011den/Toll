package bds.threads.yeld;

import bds.threads.yeld.ExtendedThread;

import java.util.Date;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("1. ======>>>>>>>> НАЧАЛО ТЕСТА БЕЗ YELD <<<<<<<<<<<===========");
        ExtendedThread extendedThread1 = new ExtendedThread();
        extendedThread1.start();

        for (int i=0; i<5; i++)
        {
            System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - занимает устройство");
        }

        extendedThread1.join();
        System.out.println("1. ======>>>>>>>> КОНЕЦ ТЕСТА БЕЗ YELD <<<<<<<<<<<===========");



        System.out.println("2. ======>>>>>>>> НАЧАЛО ТЕСТА С YELD <<<<<<<<<<<===========");
        ExtendedThread extendedThread2 = new ExtendedThread();
        extendedThread2.start();

        for (int i=0; i<5; i++)
        {
            Thread.yield();
            System.out.println((new Date()).toString() + " : "+ Thread.currentThread().getName() + " - занимает устройство");
        }

        extendedThread2.join();
        System.out.println("2. ======>>>>>>>> КОНЕЦ ТЕСТА С YELD <<<<<<<<<<<===========");
    }

}
