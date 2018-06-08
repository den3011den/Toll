package bds.services;

// Сервис отправки сообщений должен выполнять следующие функции:
//
//       * каждую минуту (периодичность – опция настройки) отправлять накопленные данные на центральный сервер
//          в формате json (пока реальная отправка не делается, объект забирается из очереди Сервиса хранения
//          (SavingMessagesService) и содержание объекта выводится в лог в формате json).
//

import bds.GpsContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SendingMessagesService {

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(SendingMessagesService.class);

    // забрать из очереди точку
    private String getPointFromQueue() throws IOException, InterruptedException{
        return GpsContext.savingMessagesService.takePointDTOFromQueue();
    }

    // послать точку серверу
    private void sendPointToServer(String pointToSendInJson) throws IOException, InterruptedException{
        LOG.info("SendingMessagesService: " + pointToSendInJson);
    }


    // по рассписанию - забор точки из очереди и отправка точки серверу
    @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
     private void tick() throws IOException, InterruptedException {

        LOG.info("SendingMessagesService.tick");

        // взять точку
        String jsonPointDTO = getPointFromQueue();

        // послать точку на сервер
        sendPointToServer(jsonPointDTO);

     }
}
