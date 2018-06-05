package bds.services;

import bds.GpsContext;
import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    // стартовая latGps
    private double latGps = 56.481091;
    // приращение latGps на шаге
    private double latGpsDelta = 0.00001;
    // стартовая lonGps
    private double lonGps = 84.978195;
    // приращение lonGps на шаге
    private double lonGpsDelta = 0.000005;
    // номер авто
    private String autoIdGps = "Ж777ЖД70";

    // выдаёт по рассписанию параметры точки
    // с записью в очередь сервиса SavingMessagesService
    @Scheduled(cron = "${gpstracker.peekSchedule.cron.prop}")
    private void tick() throws JsonProcessingException, InterruptedException {

        System.out.println("GpsService.tick " );

        latGps = latGps + latGpsDelta;
        lonGps = lonGps + lonGpsDelta;

        // конструкция объекта PointDTO с инициализацией сразу по всем полям
        PointDTO newPoint = new PointDTO(latGps, lonGps, autoIdGps, System.currentTimeMillis());

        // запись в очередь сервиса ЫavingMessagesService
        GpsContext.savingMessagesService.putPointDTOIntoQueue(newPoint);
    }
}
