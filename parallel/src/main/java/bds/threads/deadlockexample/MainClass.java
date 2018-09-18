package bds.threads.deadlockexample;

import static java.lang.Math.random;

public class MainClass {
    public static void main(String[] args) {

        System.out.println(" --- Начало main");
        //создаем заданное число нитей, и запускаем их на выполнение
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            int randVar = (int) (random() * 10);
            Runnable transaction = new ForDeadLock(randVar);
            threads[i] = new Thread(transaction);
            threads[i].start();
        }

        System.out.println(" --- Конец main");
    }
}
