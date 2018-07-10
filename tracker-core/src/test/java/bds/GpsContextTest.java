package bds;

import bds.services.GpsService;
import bds.services.SendingMessagesService;
import org.junit.Test;
import org.springframework.scheduling.TaskScheduler;

import java.io.UnsupportedEncodingException;

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
}