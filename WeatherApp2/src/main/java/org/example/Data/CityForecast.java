package org.example.Data;

public class CityForecast extends BaseClass {
        private Integer temperature;
        private Integer pressure;
        private Integer humidity;
        private String windDirection;
        private Integer windSpeed;

    public CityForecast(String country, String city, Integer temperature, Integer pressure, Integer humidity, String windDirection, Integer windSpeed) {
        super(country, city);
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public CityForecast(String country, String city, Integer temperature, Integer pressure, Integer humidity, Integer windDirection, Integer windSpeed) {
        super(country, city);
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windDirection = String.valueOf(windDirection);
        this.windSpeed = windSpeed;
    }

        public Integer getTemperature() {
        return temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                "CityForecast{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed=" + windSpeed +
                '}';
    }
}

