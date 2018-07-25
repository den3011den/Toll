package bds;

public class AddTrackForm {

    private String autoId;
    private double lat;
    private double lon;
    private long time;

    public AddTrackForm() {

    }

    public AddTrackForm(String autoId, double lat, double lon, long time) {
        this.autoId = autoId;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
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

    public void setTime(long time) {
        this.time = time;
    }
}
