package bds.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jdev on 06.03.2017.
 */
public class PointDTOTest {

    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1526751215894}";
    private String autoID = "o567gfd";

//    @Test
//    public void toJson() throws Exception {
//        PointDTO point = new PointDTO();
//        point.setLat(56);
//        point.setLon(74);
//        point.setAutoId("o567gfd");
//        point.setTime(System.currentTimeMillis());
//        assertTrue(point.toJson().contains("\"lat\":56"));
//        assertTrue(point.toJson().contains("\"time\":"));
//        System.out.println(point.toJson());
//    }
//
//    @Test
//    public void decodeDto() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        PointDTO dto = mapper.readValue(expected, PointDTO.class);
//        assertEquals(autoID, dto.getAutoId());
//        assertEquals(1526751215894L, dto.getTime());
//    }
}