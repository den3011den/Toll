package bds;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GpsTrackerCoreMain {
        public static void main(String... args) throws InterruptedException {
            ApplicationContext context = new AnnotationConfigApplicationContext(GpsContext.class);
        }
}
