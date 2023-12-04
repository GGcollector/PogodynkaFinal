package org.example;


import org.example.Data.CityForecast;
import org.example.Data.CityForecastMapper;
import org.example.Data.CityLocalization;
import org.example.Data.CityLocalizationMapper;
import org.example.Logic.OpenWeatherApiClient;
import org.example.Logic.ServiceA;
import org.example.Logic.WeatherStackApiClient;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Podaj miasto: ");
        String city = ServiceA.getCityName();
//        System.out.println("Podaj kraj: ");
//        String country =ServiceA.getCountryName();
//        System.out.println("Podaj szerokość geog.(lat): ");
//        double lat = ServiceA.getLat();
//        System.out.println("Podaj długość geog.(lon): ");
//        double lon = ServiceA.getLon();
//
//        ServiceA.saveCityDataToFile("daneTestowe.csv", true,"miasto", city, "country", country, "lat", String.valueOf(lat), "lon", String.valueOf(lon));
//        ServiceA.loadCityDataFromFile("daneTestowe.csv");

//      API's  ....................................................................................

        WeatherStackApiClient weatherStackApiClientScanner = new WeatherStackApiClient();

        CityForecastMapper weatherToForecastMapper21 = new CityForecastMapper(); //1
        HttpResponse<String> responseWEScanner = weatherStackApiClientScanner.getWeatherViaScanner(city); //2
//        System.out.println(responseOPScanner.body());

        CityForecast forecastWeScanner = weatherToForecastMapper21.mapToForecast(responseWEScanner.body());
        System.out.println("To jest forecast OpenWeatherViaScanner: " + forecastWeScanner.toString());


        CityLocalizationMapper localizationMapper = new CityLocalizationMapper(); //1
        HttpResponse<String> responseWeatherStack = weatherStackApiClientScanner.getWeatherViaScanner(city); //2
        CityLocalization zmienna = localizationMapper.mapToForecast1(responseWeatherStack.body());
        System.out.println("zmienna " + zmienna.toString());


//        WeatherStackApiClient weatherStackApiClient = new WeatherStackApiClient();
//        CityForecastMapper cityForecastMapper = new CityForecastMapper();
//        final HttpResponse<String> response = weatherStackApiClient.getWeather("Kutno");
//        System.out.println(response.body());

//        CityForecast forecast = cityForecastMapper.mapToForecast(response.body());
//        System.out.println("To jest forecast WeatherStack: " + forecast.toString());

//        OpenWeatherApiClient openWeatherApiClient = new OpenWeatherApiClient();
//        WeatherToForecastMapper2 weatherToForecastMapper2 = new WeatherToForecastMapper2();
//        HttpResponse<String> responseOP = openWeatherApiClient.getWeather("Kutno");
////        System.out.println(responseOP.body());
//
//        Forecast forecastOP = weatherToForecastMapper2.mapToForecast(responseOP.body());
//        System.out.println("To jest forecast OpenWeather: " + forecastOP.toString());
//
//


        }
    }