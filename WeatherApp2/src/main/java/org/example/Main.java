package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import org.example.Data.*;
import org.example.Logic.OpenWeatherApiClient;
import org.example.Logic.ServiceA;
import org.example.Logic.WeatherStackApiClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice;
            System.out.println("Witaj w aplikacji pogodynka !");
            Thread.sleep(1000);
        do {
            System.out.println("Wybierz jedną z poniższych opcji: ");
            System.out.println("1. Dodaj miasto do pliku");
            System.out.println("2. Wyświetl dostępne miasta w pliku");
            System.out.println("3. Pokaż prognozę pogody API");
            System.out.println("4. Pokaż prognozę pogody w pliku");
            System.out.println("5. Pokaż prognozę pogody na podstawie współrzędnych");
            System.out.println("6. Zakończ aplikacje");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
        System.out.println("Podaj miasto: ");
        String city = ServiceA.getCityName();
        System.out.println("Podaj kraj: ");
        String country = ServiceA.getCountryName();
        System.out.println("Podaj szerokość geog.(lat): ");
        double lat = ServiceA.getLat();
        System.out.println("Podaj długość geog.(lon): ");
        double lon = ServiceA.getLon();
        ServiceA.saveCityDataToFile("daneTestowe.csv", true,"miasto", city, "country", country, "lat", String.valueOf(lat), "lon", String.valueOf(lon));

                    break;
                case 2:
            // todo dodaj formułę, gdyby plik był pusty albo nie istniał
        ServiceA.loadCityDataFromFile("daneTestowe.csv");
                    break;

                case 3:
                    System.out.println("Podaj nazwę miasta dla, którego chcesz prognozę pogody: ");
                    String userCity = ServiceA.getCityName();
                    // todo to poniższe to bym wywalił do metody w serviceA
                    // todo ta poniższa metoda to raczej pasuje do case'u 2
                    // poniższe sczytuje wszystkie miasta z pliku z miastami
                    List<String> cityInfo = new ArrayList<>(); // tworzymy arraylistę
                    try (BufferedReader reader = new BufferedReader(new FileReader("danetestowe.csv"))) { //podajemy plik, na którym ma się skupić
                        String line; // tworzymy stringa
                        while ((line = reader.readLine()) != null) { // przypisujemy stringa do linijki w pliku i sprawdzamy czy nie jest pusty
                            final String[] split = line.split(","); // towrzymy tablicę stringów i usuwamy przecinki (?)
                            cityInfo.add(split[1]); // dodajemy listy pozycję 1 z tablicy czyli nazwę miast.
                        }
                        System.out.println(cityInfo);
                    }
                    break;

                case 4:


                    break;

                case 5:
                    break;
                case 6:
                    System.out.println("Dziękujemy za korzystanie z usług aplikacji");
                    break;
                default:
                    System.out.println("Nie ma takiej komendy");
                    break;
            }

        } while (choice != 6);

        scanner.close();

//

//      API's  ....................................................................................
//# WEATHERSTACK
// Forecast pogodowy
        WeatherStackApiClient weatherStackApiClientScanner = new WeatherStackApiClient();
        CityForecastMapperWeatherStack weatherToForecastMapper21 = new CityForecastMapperWeatherStack(); // krok 1
//        HttpResponse<String> responseWEScanner = weatherStackApiClientScanner.getWeatherViaScanner(city); // krok 2
//        System.out.println(responseOPScanner.body());

//        CityForecast forecastWeScanner = weatherToForecastMapper21.mapToForecast(responseWEScanner.body()); // krok 3

//        System.out.println("To jest forecast WeatherStackViaScanner: " + forecastWeScanner.toString());

// Lokalizacja miasta (dane geograficzne)
//
//        CityLocalizationMapperWeatherStack localizationMapper = new CityLocalizationMapperWeatherStack(); //1
//        HttpResponse<String> responseWeatherStack = weatherStackApiClientScanner.getWeatherViaScanner(city); //2
//        CityLocalization CityLocationInfo = localizationMapper.mapToForecast1(responseWeatherStack.body()); // 3
//
//        System.out.println("CityLocationInfo WeatherStack: " + CityLocationInfo.toString());

// forecast bez skanera
//        WeatherStackApiClient weatherStackApiClient = new WeatherStackApiClient();
//        CityForecastMapperWeatherStack cityForecastMapperWeatherStack = new CityForecastMapperWeatherStack();
//        final HttpResponse<String> response = weatherStackApiClient.getWeather("Kutno");System.out.println(response.body());
//
//        CityForecast forecast = cityForecastMapperWeatherStack.mapToForecast(response.body());
//        System.out.println("To jest forecast WeatherStack: " + forecast.toString());

//# OPENWEATHER

//        OpenWeatherApiClient openWeatherApiClient = new OpenWeatherApiClient();
//        CityForecastMapperOpenW weatherToForecastMapper2 = new CityForecastMapperOpenW();
//        HttpResponse<String> responseOP = openWeatherApiClient.getWeatherViaScanner(city);
//
//
//        CityForecast forecastOP = weatherToForecastMapper2.mapToForecast(responseOP.body());
//        System.out.println("To jest forecast OpenWeather: " + forecastOP.toString());
//
//        CityLocalizationMapperOpenW localizationMapperOP = new CityLocalizationMapperOpenW(); //1
//        HttpResponse<String> responseOpenW = openWeatherApiClient.getWeatherViaScanner(city); //2
//        CityLocalization CityLocationInfoOpenW = localizationMapperOP.mapToForecast1(responseOpenW.body()); // 3
//
//        System.out.println("CityLocationInfo OpenWeather: " + CityLocationInfoOpenW.toString());


        }
    }