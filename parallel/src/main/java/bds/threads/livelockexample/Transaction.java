package bds.threads.livelockexample;

class Transaction implements Runnable {
    private BankAccount sourceAccount, destinationAccount;
    private double amount;

    Transaction(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.tryTransfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed ", Thread.currentThread().getName());
    }

}