package bds.threads.deadlockexample;

public class ForDeadLock implements Runnable {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    int flag;

    public ForDeadLock(int flag) {
        this.flag = flag;
        System.out.println(Thread.currentThread().getName() + ": flag = " + flag);
    }

   private void testMetod1() {

        System.out.println(Thread.currentThread().getName() + ": запуск testMetod1()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void testMetod2() {

        String name = Thread.currentThread().getName();
        System.out.println(Thread.currentThread().getName() + ": запуск testMetod2()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (flag < 5) {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": заблокировал lock1");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": заблокировал lock1 и lock2");
                    testMetod1();
                    testMetod2();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": освободил lock1 и lock2");
         }
        else {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + ": заблокировал lock2");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + ": заблокировал lock2 и lock1");
                    testMetod2();
                    testMetod1();
                }
                System.out.println(Thread.currentThread().getName() + ": освободил lock2 и lock1");
            }
        }

    }
 }

