package bds.threads.starvationexample;

// Запускается 10000 потоков AddingClass, увеличивающих счётчик countVar на 1
// и 1 поток DisplayingClass - просто выводящий значение счётчка countVar на экран, но
// с минимальным приоритетом
//
// При выстром запуске видно, что конкурируя одновременно за один и тот же ресурс
// потоки позволяют выполниться DisplayingClass самым последним.
// Если в AddingClass ещё добавить задержку, то выполнения потока DisplayingClass
// придётся ждать долго. Если, например, этот поток возвражает данные пользователю
// в рамках web-приложения, то пользователь не дождётся результата. Результат может и вернётся
// когда пользователь уже уйдёт, что для пользователя будет равносильно отказу в обслуживании,
// хотя система вроде как в рабочем состоянии.

public class MainClass {

    static long countVar = 0;
    static Object lock = new Object();

    public static void main(String[] args) {

        System.out.println(" --- Начало main");
        //создаем заданное число нитей, и запускаем их на выполнение

        Thread[] threads = new Thread[10_001];
        for (int i = 0; i < 10_000; i++) {
            Runnable transaction;
            transaction = new AddingClass();
            threads[i] = new Thread(transaction);
            threads[i].setPriority(Thread.MAX_PRIORITY);
            threads[i].start();
        }

        Runnable transaction = new DisplayingClass();
        threads[10_000] = new Thread(transaction);
        threads[10_000].setPriority(Thread.MIN_PRIORITY);
        threads[10_000].start();

        System.out.println(" --- Конец main");
    }

}

