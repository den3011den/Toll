package bds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"bds.services","bds.controllers", "bds"})
public class GpsTrackerCoreMain{

    public static void main(String[] args) {
        SpringApplication.run(GpsTrackerCoreMain.class, args);
    }

}