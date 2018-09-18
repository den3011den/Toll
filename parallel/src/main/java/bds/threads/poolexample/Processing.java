package bds.threads.poolexample;

import java.util.concurrent.Callable;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;

// Класс, который будет иммитировать бурную деятельность
public class Processing implements Callable {

    @Override
    public Double call() throws Exception {

        System.out.println(Thread.currentThread().getName() + ": запуск");

        Double retVar = new Double(0.0D);

        for(int i=0; i < 8_000_000;i++) {
            retVar = retVar + sqrt(i + random()*5);
        }

        System.out.println(Thread.currentThread().getName() + ": возвращаемый результат = " + retVar.toString());

        return retVar;
    }
}
