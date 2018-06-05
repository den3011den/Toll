package bds.services;

import bds.GpsContext;
import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import static java.lang.System.currentTimeMillis;


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

    private double latGps = 56.481091;
    private double latGpsDelta = 0.00001;
    private double lonGps = 84.978195;
    private double lonGpsDelta = 0.000005;
    private String autoIdGps = "Ж777ЖД70";

//    @Autowired
//    private SavingMessagesService savingMessagesService;

    @Scheduled(cron = "${gpstracker.peekSchedule.cron.prop}")
    private void tick() throws JsonProcessingException, InterruptedException {

        System.out.println("GpsService.tick " );

        latGps = latGps + latGpsDelta;
        lonGps = lonGps + lonGpsDelta;
        PointDTO newPoint = new PointDTO(latGps, lonGps, autoIdGps, System.currentTimeMillis());
        GpsContext.savingMessagesService.putPointDTOIntoQueue(newPoint);

    }

}
