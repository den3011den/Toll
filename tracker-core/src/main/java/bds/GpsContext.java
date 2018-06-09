package bds;

import bds.services.SavingMessagesService;
import bds.services.GpsService;
import bds.services.SendingMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.UnsupportedEncodingException;


@Configuration
@EnableScheduling
@PropertySource("classpath:/trackercore.properties")
public class GpsContext {

    @Autowired
    public static SavingMessagesService savingMessagesService;

    @Bean
    public GpsService gpsService() throws UnsupportedEncodingException {
        return new GpsService();
    }

    @Bean
    public SendingMessagesService sendingMessagesService() {
        return new SendingMessagesService();
    }


    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(20);
        return scheduler;
    }



}
