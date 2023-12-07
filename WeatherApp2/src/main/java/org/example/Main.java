package org.example;


import org.example.Logic.ServiceA;

import java.io.IOException;
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
        String city = ServiceA.provideCityName();
        System.out.println("Podaj kraj: ");
        String country = ServiceA.getCountryName();
        System.out.println("Podaj szerokość geog.(lat): ");
        double lat = ServiceA.getLat();
        System.out.println("Podaj długość geog.(lon): ");
        double lon = ServiceA.getLon();
        ServiceA.saveData("daneTestowe.csv", true,"miasto", city, "country", country, "lat", String.valueOf(lat), "lon", String.valueOf(lon));

                    break;
                case 2:
            // todo dodaj formułę, gdyby plik był pusty albo nie istniał
            ServiceA.loadCityDataFromFile("daneTestowe.csv");

                    break;

                case 3:
                    System.out.println("oto dostępna lista: ");
                    ServiceA.loadCityDataFromFile("daneTestowe.csv");
                    System.out.println("Wybierz miasto dla, którego chcesz prognozę pogody:");
                    String userCity = ServiceA.provideCityName();
                    String averageWeatherResult = ServiceA.getAverageWeatherResult(userCity);
                    ServiceA.saveData("ZbiorczyForecast.csv", true, "pogoda", averageWeatherResult);
                    System.out.println("dane zapisane do pliku");

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

// forecast bez skanera
//        WeatherStackApiClient weatherStackApiClient = new WeatherStackApiClient();
//        CityForecastMapperWeatherStack cityForecastMapperWeatherStack = new CityForecastMapperWeatherStack();
//        final HttpResponse<String> response = weatherStackApiClient.getWeather("Kutno");System.out.println(response.body());
//
//        CityForecast forecast = cityForecastMapperWeatherStack.mapToForecast(response.body());
//        System.out.println("To jest forecast WeatherStack: " + forecast.toString());


        }
    }