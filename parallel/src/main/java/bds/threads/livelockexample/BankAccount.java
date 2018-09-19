package bds.threads.livelockexample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// Пример полностью из интернета
//
// Смысл в том, что получается как бы бесконечный цикл, хотя в явном виде такового неи
// Поток пытается заблокировать ресурс, но видя блокировку другим потоком, отказываются от своих изменений
// В свою очередь другой поток делает то же самое - видя блокировку или отказ от изменений другого потока - отказывается от своих изменений
// Так бесконечно они откатывают друг друга. Похоже на deadlock
//
public class BankAccount {
    double balance;
    int id;
    Lock lock = new ReentrantLock();

    BankAccount(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    boolean withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " : withdraw() - вход");
        if (this.lock.tryLock()) {
            try {Thread.sleep(100l);} catch (InterruptedException e) {}
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " : withdraw() - удача - выход");
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " : withdraw() - НЕ удача - выход");
        return false;
    }

    boolean deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " : deposit() - вход");
        if (this.lock.tryLock()) {
            try {Thread.sleep(100l);} catch (InterruptedException e) {}
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " : deposit() - удача - выход");
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " : deposit() - НЕ удача - откат - выход");
        return false;
    }

    public boolean tryTransfer(BankAccount destinationAccount, double amount) {
        if (this.withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                this.deposit(amount);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final BankAccount fooAccount = new BankAccount(1, 500d);
        final BankAccount barAccount = new BankAccount(2, 500d);

        new Thread(new Transaction(fooAccount, barAccount, 10d), "transaction-1").start();
        new Thread(new Transaction(barAccount, fooAccount, 10d), "transaction-2").start();

    }

}