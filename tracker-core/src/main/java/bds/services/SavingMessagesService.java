package bds.services;

import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
// import java.util.concurrent.TimeUnit;

//  Сервис хранения сообщений должен выполнять следующие функции:
//
//      * предоставлять интерфейс для записи текущих параметров транспорта
//          (GPS), а также для извлечения параметров в том же порядке,
//          в каком они были записаны.
//          На данный момент внутренняя реализация предполагает использование блокирующей очереди.
//

@Service
public class SavingMessagesService {

    // очередь
    private BlockingDeque<String> queue =  new LinkedBlockingDeque<>(100);
    //private int putCount;
    //private long previous;

    // забрать из очереди
    public PointDTO takePointDTOFromQueue() throws IOException, InterruptedException {
        //log.info("take trying!!!");

        String jsonString = queue.take();
        return PointDTO.fromJSON(jsonString);

    }

    // положить в очередь
    public void putPointDTOIntoQueue(PointDTO gpsObject) throws JsonProcessingException, InterruptedException {
        //log.info("ScheduledQueueService.put " + i);
        queue.put(gpsObject.toJson());
    }

}
