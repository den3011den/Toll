package bds.controllers;

import bds.dto.PointDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServerCoreControllerTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ServerCoreController mockedController;

    @Test
    public void coords() {

        PointDTO pointDTO = new PointDTO(11.1, 22.2, "sdsd", 777L);

        String rezult = mockedController.coords(pointDTO, true);

        System.out.println(rezult);
        assertEquals("{success:\"true\"}", rezult);
    }
}