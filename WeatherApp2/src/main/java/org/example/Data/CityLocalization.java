package org.example.Data;

public class CityLocalization extends BaseClass {
    private String lat;
    private String lon;

    public CityLocalization(String country, String city, String lat, String lon) {
        super(country, city);
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return super.toString() + " " + "CityLocalization{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
