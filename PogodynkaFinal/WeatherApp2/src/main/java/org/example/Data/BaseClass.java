package org.example.Data;

import java.util.Scanner;
import java.util.UUID;

public abstract class BaseClass {

    private String city;
    private String country;
    private UUID id;

    public BaseClass(String country, String city) {
        this.country = country;
        this.city = city;
        this.id = createUuid(country, city);
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public UUID getId() {
        return id;
    }

    private UUID createUuid(String country, String city) {
        return UUID.nameUUIDFromBytes((country + city).getBytes());
    }

    @Override
    public String toString() {
        return "BaseClass{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                '}';
    }

}
