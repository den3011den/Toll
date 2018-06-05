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

import static bds.GpsContext.savingMessagesService;

@Service
public class SendingMessagesService {

        @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
        private void tick() throws IOException, InterruptedException {

            System.out.println("SendingMessagesService.tick " );

            String jsonPointDTO;
            jsonPointDTO = GpsContext.savingMessagesService.takePointDTOFromQueue();

        }

    }

