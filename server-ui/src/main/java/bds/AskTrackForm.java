package bds;

public class AskTrackForm {

    private String autoId;
    private int lastPointsQuantity;

    public AskTrackForm() {

    }

    public AskTrackForm(String autoId, int lastPointsQuantity) {
        this.autoId = autoId;
        this.lastPointsQuantity = lastPointsQuantity;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setautoId(String autoId) {
        this.autoId = autoId;
    }

    public int getLastPointsQuantity() {
        return lastPointsQuantity;
    }

    public void setLastPointsQuantity(int lastPointsQuantity) {
        this.lastPointsQuantity = lastPointsQuantity;
    }

}
