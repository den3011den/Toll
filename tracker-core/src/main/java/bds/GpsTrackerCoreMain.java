package bds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//public class GpsTrackerCoreMain {
//        public static void main(String... args) throws InterruptedException {
//            ApplicationContext context = new AnnotationConfigApplicationContext(GpsContext.class);
//        }
//}

@SpringBootApplication
@ComponentScan({"bds"})
public class GpsTrackerCoreMain{

    public static void main(String[] args) {
        SpringApplication.run(GpsTrackerCoreMain.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}