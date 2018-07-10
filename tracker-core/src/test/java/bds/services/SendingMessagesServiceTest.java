package bds.services;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class SendingMessagesServiceTest {

    private RestTemplateBuilder builder = new RestTemplateBuilder();

    @Test
    public void restTemplate() {
        SendingMessagesService sendingMessagesService = new SendingMessagesService();
        RestTemplate rezult = sendingMessagesService.restTemplate(builder);
        assertNotNull(rezult);
    }
}