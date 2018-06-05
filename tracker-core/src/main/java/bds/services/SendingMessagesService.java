package bds.services;

// Сервис отправки сообщений должен выполнять следующие функции:
//
//       * каждую минуту (периодичность – опция настройки) отправлять накопленные данные на центральный сервер
//          в формате json (пока реальная отправка не делается, объект забирается из очереди Сервиса хранения
//          (SavingMessagesService) и содержание объекта выводится в лог в формате json).
//


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SendingMessagesService {

//    @Autowired
//    private SavingMessagesService savingMessagesService;

        @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
        private void tick() {
            System.out.println("SendingMessagesService.tick " );
        }

    }

