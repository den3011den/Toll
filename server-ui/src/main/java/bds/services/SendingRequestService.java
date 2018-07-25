package bds.services;

import bds.dto.RequestAddingPoint;
import bds.dto.RequestAutoIDTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SendingRequestService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // Лoг
    private static final Logger LOG = LoggerFactory.getLogger(SendingRequestService.class);

    // послать запрос на сервер получения трэка по номеру авто
    public String sendTrackRequestToServer(RequestAutoIDTrack requestAutoIDTrack) throws IOException, InterruptedException {

        LOG.info("will send request to server: " + requestAutoIDTrack.toString());

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/coordsbyautoid";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String answer = "";

        HttpEntity<String> entity = new HttpEntity<String>(requestAutoIDTrack.toJson(), headers);
        try {
            answer = restTemplate.postForObject(url, entity, String.class);
            LOG.info("sent request to server: " + requestAutoIDTrack.toJson());
            LOG.info("server answer: " + answer);
        } catch (ResourceAccessException ee) {
            LOG.info("cannot send. Connection refused for: " + url);
        } catch (HttpClientErrorException ee) {
            LOG.info("cannot send. Error 404 for: " + url);
        }

        return answer;
    }

    public String sendAddPointRequestToServer(RequestAddingPoint requestAddingPoint) throws IOException, InterruptedException {

        LOG.info("will send request to server: " + requestAddingPoint.toString());

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/addingtrackpoint";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String answer = "";

        HttpEntity<String> entity = new HttpEntity<String>(requestAddingPoint.toJson(), headers);
        try {
            answer = restTemplate.postForObject(url, entity, String.class);
            LOG.info("sent request to server: " + requestAddingPoint.toJson());
            LOG.info("server answer: " + answer);
        } catch (ResourceAccessException ee) {
            LOG.info("cannot send. Connection refused for: " + url);
        } catch (HttpClientErrorException ee) {
            LOG.info("cannot send. Error 404 for: " + url);
        }

        return answer;
    }


}
