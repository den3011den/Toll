package bds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


// Сервис GPS должен иметь следующие параметры:
//
//     * каждую секунду (или минуту, как определено в настройке)
//          эмулировать значения текущей широты, долготы, азимута и
//          мгновенной скорости (так же как это делают настоящие приборы GPS);
//
//      * помещать значения широты, долготы, азимута и мгновенной скорости
//          в очередь, предоставляемую Сервисом хранения (SavingMessagesService).
//


@Service
public class GpsService {

//    @Autowired
//    private SavingMessagesService savingMessagesService;

    @Scheduled(cron = "${gpstracker.peekSchedule.cron.prop}")
    private void tick() {
        System.out.println("GpsService.tick " );
    }

}
