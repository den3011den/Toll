package bds.services;

import bds.GpsTrackerCoreMain;
import bds.dao.TrackPoint;
import bds.dao.repo.TrackRepository;
import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

//  Сервис хранения сообщений должен выполнять следующие функции:
//
//      * предоставлять интерфейс для записи текущих параметров транспорта
//          (GPS), а также для извлечения параметров в том же порядке,
//          в каком они были записаны.
//          На данный момент внутренняя реализация предполагает использование блокирующей очереди.
//

@Service
public class SavingMessagesService {

     // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(SavingMessagesService.class);

    // очередь - статическая - одна для всех
    private static BlockingDeque<String> queue =  new LinkedBlockingDeque<>(100);

    // забрать из очереди
    public static String takePointDTOFromQueue() throws IOException, InterruptedException {

        String jsonString = queue.take();

        LOG.info("took data from queue: " + jsonString);

        return jsonString;
    }

    // положить в очередь
    public static void putPointDTOIntoQueue(PointDTO gpsObject) throws JsonProcessingException, InterruptedException {


        LOG.info("put data into queue " + gpsObject.toJson());
        queue.put(gpsObject.toJson());
     }



}
