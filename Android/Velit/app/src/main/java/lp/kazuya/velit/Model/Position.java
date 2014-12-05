package lp.kazuya.velit.Model;

import java.io.Serializable;

/**
 * Created by Kazuya on 17/10/2014.
 */
public class Position implements Serializable {
    private double lat;
    private double lng;

    public Position(){
        lat = 0;
        lng = 0;
    }

    public Position(double lat, double lng){

        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
