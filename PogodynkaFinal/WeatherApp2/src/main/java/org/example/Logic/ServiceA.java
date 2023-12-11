package org.example.Logic;

import org.example.Data.*;

import java.io.*;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ServiceA implements LocalizationToFile {

    public static void saveData(String file, boolean shouldAppend, String... arguments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, shouldAppend))) {
            StringBuilder line = new StringBuilder();
            for (String argument : arguments) {
                line.append(argument).append(",");
            }
            line.append("\n");
            writer.write(line.toString());
        }
    }

    public static void updateFileWithNewData(String file, String... arguments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            StringBuilder line = new StringBuilder();
            for (String argument : arguments) {
                line.append(argument).append(",");
            }
            line.append("\n");
            writer.write(line.toString());
        }
    }

    public static void checkForCityInAFile(String userCity2) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new FileReader("ZbiorczyForecast.csv"));
        String currentLine;
        boolean cityFound = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] fields = currentLine.split(",");

            for (String field : fields) {
                if (field.contains(userCity2)) {
                    cityFound = true;
                    break;
                }
            }
            if (cityFound) {
                loadForecastFromFile("ZbiorczyForecast.csv");
                break;
            }
        }
        if (!cityFound) {
            System.out.println("prognoza dla danego miasta nie jest dostępna");
            System.out.println("wyszukuje dane....");
            Thread.sleep(1000);
            System.out.println("Oto prognoza pogody dla wskazanego miasta");
            ServiceA.getAverageWeatherResult(userCity2);
        }
    }

    public static void checkForCityCase5(String userCity3) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new FileReader("ZbiorczyForecast.csv"));
        String currentLine;
        boolean cityFound = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] fields = currentLine.split(",");

            for (String field : fields) {
                if (field.contains(userCity3)) {
                    cityFound = true;
                    break;
                }
            }
            if (cityFound) {
                // tutaj nowy search gdzie spojrzy też na dane geometyczne czy się zgadzają. i zrobi Apicall do serwisu na podstawie danych
                loadForecastFromFile("ZbiorczyForecast.csv");
                break;
            }
        }
        if (!cityFound) {
            System.out.println("prognoza dla danego miasta nie jest dostępna");
            System.out.println("mogę wyszukać prognozę po współrzędnych geometrycznych");

        }
    }


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
        System.out.println("Oto forecast dla wskazanego miasta " + Files.readAllLines(Path.of(file), StandardCharsets.UTF_8));
    }


    public static int getWeatherFromWeatherStack(String userCity) throws IOException, InterruptedException {
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

    public static float getWeatherFromOpenWeather(String userCity) throws IOException, InterruptedException {
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

    public static String getWeatherFromOpenWeatherThroughCoordinates(float lat, float lon) throws IOException, InterruptedException {
        OpenWeatherApiClient openWeatherApiClient = new OpenWeatherApiClient();
        CityForecastMapperOpenW weatherToForecastMapper3 = new CityForecastMapperOpenW();
        HttpResponse<String> responseOP = openWeatherApiClient.getWeatherThroughCoords(lat, lon);
//        HttpResponse<String> responseOP = openWeatherApiClient.getWeatherThroughCoords(52.13F, 19.21F);
//        System.out.println(responseOP.body());

        CityForecast forecastOP = weatherToForecastMapper3.mapToForecast(responseOP.body());
        return forecastOP.toString();


// Lokalizacja miasta (dane geograficzne)
//        CityLocalizationMapperOpenW localizationMapperOP = new CityLocalizationMapperOpenW(); //1
//        HttpResponse<String> responseOpenW = openWeatherApiClient.getWeatherViaScanner(userCity); //2
//        CityLocalization CityLocationInfoOpenW = localizationMapperOP.mapToForecast1(responseOpenW.body()); // 3

//        System.out.println("CityLocationInfo OpenWeather: " + CityLocationInfoOpenW.toString());

    }

    public static String getAverageWeatherResult (String userCity) throws IOException, InterruptedException {
        String result = "średnia temperatura dla miasta " + userCity + " wynosi: "
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
    public static float getLat() {
         // todo lepszy były try catch z rzuceniem wyjątku
        // zrobić while'a, zeby byla petla az user nie poda dobrej wartości
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        float lat = scanner.nextFloat();
         if (lat <= -90.00 || lat >= 90.00) {
            System.out.println("błąd");
            scanner.close();
        }
        return lat;
    }
    public static float getLon() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        float lon = scanner.nextFloat();
        if (lon <= -180.00 || lon >= 180.00) {
            System.out.println("Błąd: Wprowadź liczbę z zakresu -180.00 do 180.00");
            scanner.close();
        }
        return lon;
    }
}
