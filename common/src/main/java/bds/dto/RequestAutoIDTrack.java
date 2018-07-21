package bds.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
Класс для использования в запросе к server-core
с указания номера авто, по которому интеризуют данные, и количества последних точек
 */

public class RequestAutoIDTrack {

    private String autoId;
    private int lastPointsQuantity;

    public RequestAutoIDTrack (){ }

    // конструктор с инициализацией всех полей
    public RequestAutoIDTrack(String autoId, int lastPointsQuantity) {
        this.autoId = autoId;
        this.lastPointsQuantity = lastPointsQuantity;
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

    public int getLastPointsQuantity() {
        return lastPointsQuantity;
    }

    public void setLastPointsQuantity(int lastPointsQuantity) {
        this.lastPointsQuantity = lastPointsQuantity;
    }
}
