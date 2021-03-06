package bds.services;

import bds.dto.PointDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import java.io.IOException;
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

    // очередь - статическая - одна для всех
    private static BlockingDeque<String> queue =  new LinkedBlockingDeque<>(100);

    // забрать из очереди
    public static String takePointDTOFromQueue() throws IOException, InterruptedException {

        String jsonString = queue.take();
        System.out.println("SavingMessagesService.takePointDTOFromQueue");
        System.out.println("Took: " + jsonString);

        return jsonString;
    }

    // положить в очередь
    public static void putPointDTOIntoQueue(PointDTO gpsObject) throws JsonProcessingException, InterruptedException {
        System.out.println("SavingMessagesService.putPointDTOIntoQueue");
        System.out.println(gpsObject.toJson());
        queue.put(gpsObject.toJson());
    }

}
