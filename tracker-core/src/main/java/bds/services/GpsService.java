package bds.services;

import bds.GpsContext;
import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import java.io.File;
import java.util.List;

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

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(GpsService.class);

    // индекс массива с координатами
    private int coordinateIndex = -1;
    // смещение по списку координат.
    // +1 - движемся вперёд по списку
    // - 1 - движемся назад по списку
    private int shiftIndex = 1;

    // широта
    private double latGps;
    // долгота
    private double lonGps;
    // номер авто
    private String autoIdGps = "Ж777ЖД70";

    //testtrack.kml
    final Kml kml = Kml.unmarshal(new File(".\\tracker-core\\src\\main\\resources\\testtrack.kml"));
    final Placemark placemark = (Placemark) kml.getFeature();
    Point point = (Point) placemark.getGeometry();
    List<Coordinate> coordinates = point.getCoordinates();


    // выдаёт по рассписанию параметры точки
    // с записью в очередь сервиса SavingMessagesService
    @Scheduled(cron = "${gpstracker.peekSchedule.cron.prop}")
    private void tick() throws JsonProcessingException, InterruptedException {

        LOG.info("GpsService.tick ");

        coordinateIndex = coordinateIndex + shiftIndex;

        if (coordinateIndex == coordinates.size()) {
            // поехал обратно по треку
            shiftIndex = -1;
            coordinateIndex = coordinates.size() - 1;
        }

        if (coordinateIndex == -1) {
            // поехал вперёд по треку
            shiftIndex = 1;
            coordinateIndex = 0;
        }

        LOG.info(String.valueOf(coordinateIndex));

        Coordinate coordinate = coordinates.get(coordinateIndex);

        latGps = coordinate.getLatitude();
        lonGps = coordinate.getLongitude();
        //coordinate.getAltitude()

         // создание и инициализация объекта PointDTO с инициализацией сразу по всем полям
        PointDTO newPoint = new PointDTO(latGps, lonGps, autoIdGps, System.currentTimeMillis());

        // запись в очередь сервиса SavingMessagesService
        GpsContext.savingMessagesService.putPointDTOIntoQueue(newPoint);
    }

}
