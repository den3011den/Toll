package bds.security;

import bds.dto.RequestAutoIDTrack;
import bds.services.SendingRequestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan({"bds.config","bds.services"})
@PropertySource("classpath:/serverui.properties")
public class Application implements CommandLineRunner {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SendingRequestService sendingRequestService = new SendingRequestService();
        RequestAutoIDTrack requestAutoIDTrack = new RequestAutoIDTrack("Ж777ЖД70", 5);
        String gotAnswer = sendingRequestService.sendRequestToServer(requestAutoIDTrack);
        //System.out.println("--->>>  gotAnswer === " + gotAnswer);

    }
}
