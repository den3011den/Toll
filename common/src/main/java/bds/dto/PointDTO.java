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

    // конструктор по умолчанию - понадобился для перевода из json в объукт PointDTO (не мог сериализовать)
    public PointDTO() {
     }

    // конструктор с инициализацией всех полей
    public PointDTO(double lat, double lon, String autoId, long time) {
        this.lat = lat;
        this.lon = lon;
        this.autoId = autoId;
        this.time = time;
    }

    // Получить широту
    public double getLat() {
        return lat;
    }

    // Установить широту
    public void setLat(double lat) {
        this.lat = lat;
    }

    // Получить долготу
    public double getLon() {
        return lon;
    }

    // Установить долготу
    public void setLon(double lon) {
        this.lon = lon;
    }

    // Получить регистрационный номер автомобиля
    public String getAutoId() {
        return autoId;
    }

    // Установить регистрационный номер автомобиля
    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    // из объекта PointDTO в json
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

    // установить время
    public void setTime(long time) {
        this.time = time;
    }

    // получить время
    public long getTime() {
        return time;
    }
}
