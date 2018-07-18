package bds.dao;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Date;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Table(name="tracktable")
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "AUTOID")
    String autoId;

    @Column(name = "DATE")
    Date date;

    @Column(name = "TIME")
    Time time;

    @Column(name = "LATITUDE")
    float latitude;

    @Column(name = "LONGTITUDE")
    float longtitude;

    public String toString() {
        return "TrackPoint{ id=" + id + ", autoId=" + autoId + ", date=" + date +
                ", time=" + time + ", latitude=" + latitude + ", longtitude=" + longtitude + " }";
    }


    public void setAllFields(int id, String autoId, Date date, Time time, float latitude, float longtitude) {
        this.id = id;
        this.autoId = autoId;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLongtitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }





}
