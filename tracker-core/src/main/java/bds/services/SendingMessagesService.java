package bds.services;

// Сервис отправки сообщений должен выполнять следующие функции:
//
//       * каждую минуту (периодичность – опция настройки) отправлять накопленные данные на центральный сервер
//          в формате json (пока реальная отправка не делается, объект забирается из очереди Сервиса хранения
//          (SavingMessagesService) и содержание объекта выводится в лог в формате json).
//

import bds.GpsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Service
public class SendingMessagesService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(SendingMessagesService.class);

    // забрать из очереди точку
    private String getPointFromQueue() throws IOException, InterruptedException{
        return GpsContext.savingMessagesService.takePointDTOFromQueue();
    }

    // послать точку серверу
    private void sendPointToServer(String pointToSendInJson) throws IOException, InterruptedException {

        LOG.info("will send data to server: " + pointToSendInJson);

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/coords";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<String>(pointToSendInJson, headers);
        try {
            String answer = restTemplate.postForObject(url, entity, String.class);
            LOG.info("sent data to server: " + pointToSendInJson);
            LOG.info("server answer: " + answer);
        } catch (ResourceAccessException ee) {
            LOG.info("cannot send. Connection refused for: http://localhost:8080/coords");
        } catch (HttpClientErrorException ee) {
            LOG.info("cannot send. Error 404 for: http://localhost:8080/coords");
        }
    }

    // по рассписанию - забор точки из очереди и отправка точки серверу
    @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
     private void tick() throws IOException, InterruptedException {

        // взять точку
        String jsonPointDTO = getPointFromQueue();

        // послать точку на сервер
        sendPointToServer(jsonPointDTO);

     }
}
