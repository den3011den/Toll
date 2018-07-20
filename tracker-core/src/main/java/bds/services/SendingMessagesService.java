package bds.services;

// Сервис отправки сообщений должен выполнять следующие функции:
//
//       * каждую минуту (периодичность – опция настройки) отправлять накопленные данные на центральный сервер
//          в формате json (пока реальная отправка не делается, объект забирается из очереди Сервиса хранения
//          (SavingMessagesService) и содержание объекта выводится в лог в формате json).
//

import bds.GpsContext;
import bds.GpsTrackerCoreMain;
import bds.dao.TrackPoint;
import bds.dao.repo.TrackRepository;
import bds.dto.PointDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.List;

@Service
@EnableJpaRepositories("bds.dao")
@EntityScan(basePackageClasses = bds.dao.TrackPoint.class)
public class SendingMessagesService {

    @Autowired
    TrackRepository trackRepository;

    private static final Logger log = LoggerFactory.getLogger(GpsTrackerCoreMain.class);
    static private List<TrackPoint> all;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(SendingMessagesService.class);


    public void delete(TrackPoint trackPoint) {
        trackRepository.delete(trackPoint);
    }

    public void update(TrackPoint trackPoint, PointDTO pointDTO) {
        trackPoint.setFromPointDTO(pointDTO);
        trackRepository.save(trackPoint);
    }

    public void read() {
        all = (List<TrackPoint>) trackRepository.findAll();

        if (all.size() == 0) {
            log.info("NO RECORDS");
        }

        all.stream().forEach(trackPoint -> log.info(trackPoint.toString()));
    }


    public TrackPoint create(PointDTO pointDTO) {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setFromPointDTO(pointDTO);
        //System.out.println(trackPoint.toString());
        return trackRepository.save(trackPoint);
    }


    // забрать из очереди точку
    private String getPointFromQueue() throws IOException, InterruptedException{

        String jsonObject  = GpsContext.savingMessagesService.takePointDTOFromQueue();

        ObjectMapper mapper = new ObjectMapper();
        PointDTO pointDTO = mapper.readValue(jsonObject, PointDTO.class);
        create(pointDTO);
        return jsonObject;
    }

    // послать точку серверу
    private String sendPointToServer(String pointToSendInJson) throws IOException, InterruptedException {

        LOG.info("will send data to server: " + pointToSendInJson);

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/coords";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String answer = "";

        HttpEntity<String> entity = new HttpEntity<String>(pointToSendInJson, headers);
        try {
            answer = restTemplate.postForObject(url, entity, String.class);
            LOG.info("sent data to server: " + pointToSendInJson);
            LOG.info("server answer: " + answer);
        } catch (ResourceAccessException ee) {
            LOG.info("cannot send. Connection refused for: http://localhost:8080/coords");
        } catch (HttpClientErrorException ee) {
            LOG.info("cannot send. Error 404 for: http://localhost:8080/coords");
        }

        return answer;
    }

    // по рассписанию - забор точки из очереди и отправка точки серверу
    @Scheduled(cron = "${gpstracker.sendSchedule.cron.prop}")
     public String tick() throws IOException, InterruptedException {

        // взять точку
        String jsonPointDTO = getPointFromQueue();

        // послать точку на сервер
        String answer = sendPointToServer(jsonPointDTO);

        return answer;
     }
}
