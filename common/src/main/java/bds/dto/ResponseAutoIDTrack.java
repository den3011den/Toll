package bds.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;

/*
Класс для использования в ответе от server-core на запрос с использованием класса RequestAutoIDTrack
 */
public class ResponseAutoIDTrack {

    private String success;
    private String info;
    private String autoId;
    private int lastPointsQuantity;
    private List<PointDTO> points = new ArrayList<PointDTO>();

    public ResponseAutoIDTrack(){ }

    // конструктор с инициализацией всех полей
    public ResponseAutoIDTrack(String success, String info, String autoId, int lastPointsQuantity) {
        this.success = success;
        this.info = info;
        this.autoId = autoId;
        this.lastPointsQuantity = lastPointsQuantity;
    }

    // из объекта ResponseAutoIDTrack в json
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

    public void addPoint(PointDTO pointDTO) {
        points.add(pointDTO);
    }

    public PointDTO getPoint(int listIndex) {
        if (listIndex < points.size())
            return points.get(listIndex);
        else
            return new PointDTO();
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public List<PointDTO> getPoints() {
        return points;
    }

    public void setPoints(List<PointDTO> points) {
        this.points = points;
    }

}
