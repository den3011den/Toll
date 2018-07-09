package bds.dto;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/*

    Тестовый класс для /common/src/main/java/bds/dto/PointDTO

 */

public class PointDTOTest {

    @Test
    public void toJson() throws Exception {
        PointDTO pointDTO = new PointDTO(50.1, 60.1, "вв123и70", 22222L);
        assertEquals("{\"lat\":50.1,\"lon\":60.1,\"autoId\":\"вв123и70\",\"time\":22222}", pointDTO.toJson());
    }

    @Test
    public void getLat() {
        setLat();
    }

    @Test
    public void setLat() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLat(40.1);
        assertTrue(pointDTO.getLat() == 40.1);
    }

    @Test
    public void getLon() {
        setLon();
    }

    @Test
    public void setLon() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLon(20.1);
        assertTrue(pointDTO.getLon() == 20.1);
    }

    @Test
    public void getAutoId() {
        setAutoId();
    }

    @Test
    public void setAutoId() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setAutoId("аа555а70");
        assertEquals("аа555а70", pointDTO.getAutoId());
    }

    @Test
    public void toStringTest() {
        PointDTO pointDTO = new PointDTO(50.1, 60.1, "вв123и70", 22222L);
        assertEquals("{\"lat\":50.1,\"lon\":60.1,\"autoId\":\"вв123и70\",\"time\":22222}", pointDTO.toString());
    }

    @Test
    public void setTime() {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setTime(111111L);
        assertEquals(111111L, (long) pointDTO.getTime());
    }


    @Test
    public void getTime() {
        setTime();
    }
}