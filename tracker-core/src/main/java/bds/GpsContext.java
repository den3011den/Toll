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


@Configuration
@EnableScheduling
@PropertySource("classpath:/trackercore.properties")
public class GpsContext {

    @Autowired
    private SavingMessagesService savingMessagesService;

    @Bean
    public SavingMessagesService savingMessagesService() {
        return new SavingMessagesService();
    }

    @Bean
    public GpsService gpsService() {
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
