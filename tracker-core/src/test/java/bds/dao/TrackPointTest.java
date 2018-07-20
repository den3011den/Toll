package bds.dao;

import bds.dto.PointDTO;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

import static org.junit.Assert.*;

public class TrackPointTest {

    @Test
    public void toStringTest() {
        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();

        String stringForCheck = "TrackPoint{ id=0, autoId=111ff11, date=" + new Date(timeForTest) +
                ", time=" + new Time(timeForTest) + ", latitude=11.11, longtitude=22.22 }";

        trackPoint.setAllFields("111ff11", new Date(timeForTest), new Time(timeForTest), 11.11, 22.22);
        assertEquals(stringForCheck, trackPoint.toString());

    }

    @Test
    public void setFromPointDTO() {
        long timeForTest = System.currentTimeMillis();
        PointDTO pointDTO = new PointDTO(33.33, 44.44, "f222ff70fff", timeForTest);
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setFromPointDTO(pointDTO);
        assertEquals((new Double(33.33)).toString(), (new Double(trackPoint.getLatitude()).toString()));
        assertEquals((new Double(44.44)).toString(), (new Double(trackPoint.getLongtitude()).toString()));
        assertEquals("f222ff70fff", trackPoint.getAutoId());
        assertEquals((new Time(timeForTest)).toString(), trackPoint.getTime().toString());
    }

    @Test
    public void setAllFields() {

        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();
        trackPoint.setAllFields("111ff11", new Date(timeForTest), new Time(timeForTest), 11.11, 22.22);
        assertEquals("111ff11", trackPoint.getAutoId());
        assertEquals(new Date(timeForTest), trackPoint.getDate());
        assertEquals(new Time(timeForTest), trackPoint.getTime());
        assertEquals((new Double(11.11)).toString(), (new Double(trackPoint.getLatitude()).toString()));
        assertEquals((new Double(22.22)).toString(), (new Double(trackPoint.getLongtitude()).toString()));
    }

    @Test
    public void setId() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setId(55);
        assertEquals(55, trackPoint.getId());
    }

    @Test
    public void getId() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setId(55);
        assertEquals(55, trackPoint.getId());

    }

    @Test
    public void setAutoId() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setAutoId("test_number");
        assertEquals("test_number", trackPoint.getAutoId());

    }

    @Test
    public void getAutoId() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setAutoId("test_number");
        assertEquals("test_number", trackPoint.getAutoId());

    }

    @Test
    public void setDate() {
        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();
        trackPoint.setDate(new Date(timeForTest));
        assertEquals(new Date(timeForTest), trackPoint.getDate());

    }

    @Test
    public void getDate() {
        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();
        trackPoint.setDate(new Date(timeForTest));
        assertEquals(new Date(timeForTest), trackPoint.getDate());
    }

    @Test
    public void setTime() {
        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();
        trackPoint.setTime(new Time(timeForTest));
        assertEquals(new Time(timeForTest), trackPoint.getTime());
    }

    @Test
    public void getTime() {
        TrackPoint trackPoint = new TrackPoint();
        long timeForTest = System.currentTimeMillis();
        trackPoint.setTime(new Time(timeForTest));
        assertEquals(new Time(timeForTest), trackPoint.getTime());

    }

    @Test
    public void setLatitude() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setLatitude(72.72);
        assertEquals((new Double(72.72)).toString(), (new Double(trackPoint.getLatitude()).toString()));
    }

    @Test
    public void getLatitude() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setLatitude(72.72);
        assertEquals((new Double(72.72)).toString(), (new Double(trackPoint.getLatitude()).toString()));
    }

    @Test
    public void setLongtitude() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setLatitude(32.32);
        assertEquals((new Double(32.32)).toString(), (new Double(trackPoint.getLatitude()).toString()));

    }

    @Test
    public void getLongtitude() {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setLatitude(32.32);
        assertEquals((new Double(32.32)).toString(), (new Double(trackPoint.getLatitude()).toString()));

    }
}