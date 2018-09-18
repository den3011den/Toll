package bds.threads.poolexample;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

// ест пула нитей
public class MainClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        Set<Future<Double>> set = new HashSet<Future<Double>>();

        for(int i=0; i < 100;i++) {
            Callable<Double> callable = new Processing();
            Future<Double> future = pool.submit(callable);
            set.add(future);
        }

        double sum = 0;

        for (Future<Double> future : set) sum = sum + future.get().doubleValue();

        System.out.println("Сумма всех данных = " + String.valueOf(sum));

        System.exit(1);

    }

}
