package org.example;


import org.example.Logic.ServiceA;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice;
            System.out.println("Witaj w aplikacji pogodynka !");
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
                    ServiceA.updateFileWithNewData("ZbiorczyForecast.csv", "pogoda", averageWeatherResult);
                    System.out.println("dane zapisane do pliku");

                    break;

                case 4:
                    System.out.println("oto dostępna lista miast w pliku: ");
                    ServiceA.loadCityDataFromFile("daneTestowe.csv");
                    System.out.println("Wybierz miasto dla, którego chcesz prognozę pogody:");
                    String userCity2 = ServiceA.provideCityName();
                    ServiceA.checkForCityInAFile(userCity2);
                    break;

                case 5:
                    System.out.println("oto dostępna lista miast w pliku: ");
                    ServiceA.loadCityDataFromFile("daneTestowe.csv");
                    System.out.println("Wybierz miasto dla, którego chcesz prognozę pogody:");
                    String userCity3 = ServiceA.provideCityName();
                    ServiceA.checkForCityCase5(userCity3);
                    System.out.println("Podaj szerokość geog.(lat): ");
                    float latCase5 = ServiceA.getLat();
                    System.out.println("Podaj długość geog.(lon): ");
                    float lonCase5 = ServiceA.getLon();
                    System.out.println("Poniżej prognoza pogody na podstawie wskazanych współrzędnych: ");
                    String forecastViaCoords = String.valueOf(ServiceA.getWeatherFromOpenWeatherThroughCoordinates(latCase5, lonCase5));
                    System.out.println(forecastViaCoords);
                    ServiceA.updateFileWithNewData("ZbiorczyForecast.csv", "Pogoda: ", forecastViaCoords);
                    System.out.println("dane zapisane do pliku");



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