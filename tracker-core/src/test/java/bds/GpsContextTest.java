package bds;

import bds.dto.PointDTO;
import bds.services.GpsService;
import bds.services.SavingMessagesService;
import bds.services.SendingMessagesService;
import org.junit.Test;
import org.springframework.scheduling.TaskScheduler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class GpsContextTest {

    @Test
    public void gpsService() throws UnsupportedEncodingException {
        GpsContext gpsContext = new GpsContext();
        GpsService gpsServiceTest = gpsContext.gpsService();
        assertSame("bds.services.GpsService", gpsServiceTest.getClass().getName());
    }

    @Test
    public void sendingMessagesService() {
        GpsContext gpsContext = new GpsContext();
        SendingMessagesService sendingMessagesServiceTest = gpsContext.sendingMessagesService();
        assertSame("bds.services.SendingMessagesService", sendingMessagesServiceTest.getClass().getName());
    }

    @Test
    public void poolScheduler() {
        GpsContext gpsContext = new GpsContext();
        TaskScheduler taskSchedulerTest = gpsContext.poolScheduler();
        assertSame("org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler", taskSchedulerTest.getClass().getName());
    }


    @Test
    public void sendingMessagesServiceIntegrated() throws IOException, InterruptedException {
        //SavingMessagesService savingMessagesService = new SavingMessagesService();
        GpsContext gpsContext = new GpsContext();
        PointDTO pointDTO1 = new PointDTO(99.9, 88.8, "eee232ee", 999L);
        PointDTO pointDTO2 = new PointDTO(11.9, 22.8, "eee232ee", 222L);
        SavingMessagesService.putPointDTOIntoQueue(pointDTO1);
        SavingMessagesService.putPointDTOIntoQueue(pointDTO2);
        String jsonString = SavingMessagesService.takePointDTOFromQueue();
        assertEquals(pointDTO1.toString(), jsonString);
        SendingMessagesService sendingMessagesService = new SendingMessagesService();
        String answer = sendingMessagesService.tick();
        assertEquals("{success:\"true\"}", answer);
    }
}