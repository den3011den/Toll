package bds.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"bds.application","bds.controllers"})
public class ServerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerCoreApplication.class, args);
    }

}
