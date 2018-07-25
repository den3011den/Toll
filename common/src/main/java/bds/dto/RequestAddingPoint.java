package bds.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
Класс для использования в запросе к server-core
с указания номера авто, по которому интеризуют данные, и количества последних точек
 */

public class RequestAddingPoint {

    private String autoId;
    private double lat;
    private double lon;
    private long time;

    public RequestAddingPoint(){ }

    // конструктор с инициализацией всех полей
    public RequestAddingPoint(String autoId, double lat, double lon, long time) {
        this.autoId = autoId;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
     }

    // из объекта RequestAutoIDTrack в json
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // toString будет выводить объект в json
    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
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

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
