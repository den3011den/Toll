package bds.services;

// Сервис отправки сообщений должен выполнять следующие функции:
//
//       * каждую минуту (периодичность – опция настройки) отправлять накопленные данные на центральный сервер
//          в формате json (пока реальная отправка не делается, объект забирается из очереди Сервиса хранения
//          (SavingMessagesService) и содержание объекта выводится в лог в формате json).
//


import bds.GpsContext;
import bds.dto.PointDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bds.GpsContext.savingMessagesService;

@Service
public class SendingMessagesService {

    private static final Logger log = LoggerFactory.getLogger(SendingMessagesService.class);

    private String getPointFromQueue() throws IOException, InterruptedException{
        return GpsContext.savingMessagesService.takePointDTOFromQueue();
    }

    private void sendPointToServer(String pointToSendInJson) throws IOException, InterruptedException{
        log.info("SendingMessagesService: " + pointToSendInJson);
    }


    @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
     private void tick() throws IOException, InterruptedException {

        System.out.println("SendingMessagesService.tick " );

        String jsonPointDTO;
        jsonPointDTO = getPointFromQueue();
        sendPointToServer(jsonPointDTO);

     }
}

