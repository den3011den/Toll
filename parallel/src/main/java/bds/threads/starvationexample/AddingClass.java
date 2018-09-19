package bds.threads.starvationexample;

// просто добавляет к счётчику единицу
public class AddingClass extends Thread {

    @Override
    public void run() {
        synchronized (MainClass.lock) {
//            try {
//                Thread.sleep(1_000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            MainClass.countVar++;
            System.out.println(Thread.currentThread().getName() + " : MainClass.countVar++ ");
            System.out.println(Thread.currentThread().getPriority());
        }
    }
}
