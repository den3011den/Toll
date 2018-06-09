package bds.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jdev on 06.03.2017.
 */
public class PointDTO {
    private double lat;
    private double lon;
    private String autoId;
    private long time;

    // конструктор по умолчанию - понадобился для перевода из json в объукть PointDTO
    public PointDTO() {
     }


    // конструктор с инициализацией всех полей
    public PointDTO(double lat, double lon, String autoId, long time) {
        this.lat = lat;
        this.lon = lon;
        this.autoId = autoId;
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    // из объекта PointDTO в json
    public String toJson() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);

    }

//    // из json в объект PointDTO
//    public static PointDTO fromJSON (String jsonString) throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
//        PointDTO pointDTO = mapper.readValue(jsonString, PointDTO.class);
//        return pointDTO;
//
//    }



    @Override
    public String toString() {
        return "PointDTO{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", autoId='" + autoId + '\'' +
                '}';
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
