package bds.threads.starvationexample;

// просто отображает значение счётчика
public class DisplayingClass extends Thread {

         @Override
        public void run() {
            synchronized (MainClass.lock) {
                System.out.println("DisplayingClass : " + Thread.currentThread().getName() + " : MainClass.countVar = " + MainClass.countVar);
                System.out.println(Thread.currentThread().getPriority());
            }
        }
    }

