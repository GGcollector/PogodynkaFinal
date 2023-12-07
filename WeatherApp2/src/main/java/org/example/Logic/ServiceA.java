package org.example.Logic;

import org.example.Data.*;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceA implements LocalizationToFile {

     public static void saveData(String file, boolean shouldAppend, String... arguments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, shouldAppend))) {
            StringBuilder line = new StringBuilder();
            for (String argument : arguments) {
                line.append(argument).append(",");
            }
            line.append("\n");
//            line.deleteCharAt(line.length() - 2);
            writer.write(line.toString());
        }
    }

//    public static void loadCityDataFromFile(String file) throws IOException {
//         // todo tutaj chyba trzeba pozmieniać na BufferedReader'a czy coś zeby nie wyswietlał tego w jednej linii tylko rozbijał od nowej linii każdy wpis
//        System.out.println("Oto lista dostępnych miast wraz z ich danymi: " + Files.readAllLines(Path.of(file), StandardCharsets.UTF_8));
//    }
    public static void loadCityDataFromFile(String file) throws IOException {
        // poniższe sczytuje wszystkie miasta z pliku z miastami
        List<String> cityInfo = new ArrayList<>(); // tworzymy arraylistę
        try (BufferedReader reader = new BufferedReader(new FileReader("daneTestowe.csv"))) { //podajemy plik, na którym ma się skupić
            String line; // tworzymy stringa
            while ((line = reader.readLine()) != null) { // przypisujemy stringa do linijki w pliku i sprawdzamy czy nie jest pusty
                final String[] split = line.split(","); // towrzymy tablicę stringów i usuwamy przecinki (?)
                cityInfo.add(split[1]); // dodajemy listy pozycję 1 z tablicy czyli nazwę miast.
            }
            System.out.println(cityInfo);
        }
    }

    public static void loadForecastFromFile(String file) throws IOException {
        // poniższe sczytuje wszystkie miasta z pliku z miastami
        List<String> cityInfo = new ArrayList<>(); // tworzymy arraylistę
        try (BufferedReader reader = new BufferedReader(new FileReader("ZbiorczyForecast.csv"))) { //podajemy plik, na którym ma się skupić
            String line; // tworzymy stringa
            while ((line = reader.readLine()) != null) { // przypisujemy stringa do linijki w pliku i sprawdzamy czy nie jest pusty
                final String[] split = line.split(","); // towrzymy tablicę stringów i usuwamy przecinki (?)
                cityInfo.add(split[1]); // dodajemy listy pozycję 1 z tablicy czyli nazwę miast.
            }
            System.out.println(cityInfo);
        }
    }

    public static int getWeatherFromWeatherStack (String userCity) throws IOException, InterruptedException {
        WeatherStackApiClient weatherStackApiClientScanner = new WeatherStackApiClient();
        CityForecastMapperWeatherStack weatherToForecastMapper21 = new CityForecastMapperWeatherStack(); // krok 1
        HttpResponse<String> responseWEScanner = weatherStackApiClientScanner.getWeatherViaScanner(userCity); // krok 2
//        System.out.println(responseOPScanner.body());

        CityForecast forecastWeScanner = weatherToForecastMapper21.mapToForecast(responseWEScanner.body()); // krok 3
        return forecastWeScanner.getTemperature();
//        System.out.println("To jest forecast WeatherStackViaScanner: " + forecastWeScanner.toString());

// Lokalizacja miasta (dane geograficzne)

//        CityLocalizationMapperWeatherStack localizationMapper = new CityLocalizationMapperWeatherStack(); //1
//        HttpResponse<String> responseWeatherStack = weatherStackApiClientScanner.getWeatherViaScanner(userCity); //2
//        CityLocalization CityLocationInfo = localizationMapper.mapToForecast1(responseWeatherStack.body()); // 3

//        System.out.println("CityLocationInfo WeatherStack: " + CityLocationInfo.toString());
    }

    //# OPENWEATHER

    public static float getWeatherFromOpenWeather (String userCity) throws IOException, InterruptedException {
        OpenWeatherApiClient openWeatherApiClient = new OpenWeatherApiClient();
        CityForecastMapperOpenW weatherToForecastMapper2 = new CityForecastMapperOpenW();
        HttpResponse<String> responseOP = openWeatherApiClient.getWeatherViaScanner(userCity);


        CityForecast forecastOP = weatherToForecastMapper2.mapToForecast(responseOP.body());
//        System.out.println("To jest forecast OpenWeather: " + forecastOP.toString());
        return forecastOP.getTemperature();

// Lokalizacja miasta (dane geograficzne)
//        CityLocalizationMapperOpenW localizationMapperOP = new CityLocalizationMapperOpenW(); //1
//        HttpResponse<String> responseOpenW = openWeatherApiClient.getWeatherViaScanner(userCity); //2
//        CityLocalization CityLocationInfoOpenW = localizationMapperOP.mapToForecast1(responseOpenW.body()); // 3

//        System.out.println("CityLocationInfo OpenWeather: " + CityLocationInfoOpenW.toString());
    }

    public static String getAverageWeatherResult (String userCity) throws IOException, InterruptedException {
        String result = "średnia temperatura wynosi: "
                + ((getWeatherFromOpenWeather(userCity) + getWeatherFromWeatherStack(userCity))/
        2) + " stopni Celsjusza";
        System.out.println(result);
        return result;
    }


    public static String provideCityName() {
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();

        return city;
    }
    public static String getCountryName() {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        return country;
    }
    public static double getLat() {
         // todo lepszy były try catch z rzuceniem wyjątku
        // zrobić while'a, zeby byla petla az user nie poda dobrej wartości
         Scanner scanner = new Scanner(System.in);
        double lat = scanner.nextInt();
         if (lat <= -90 || lat >= 90) {
            System.out.println("błąd");
            scanner.close();
        }
        return lat;
    }
    public static double getLon() {
        Scanner scanner = new Scanner(System.in);
        double lon = scanner.nextInt();
        if (lon <= -180 || lon >= 180) {
            System.out.println("błąd");
            scanner.close();
        }
        return lon;
    }
}
