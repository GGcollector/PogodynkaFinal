package org.example.Logic;

import org.example.Data.ForecastToFile;
import org.example.Data.LocalizationToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ServiceA implements LocalizationToFile {

     public static void saveCityDataToFile(String file, boolean shouldAppend, String... arguments) throws IOException {
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

    public static void loadCityDataFromFile(String file) throws IOException {
        System.out.println("odczyt z pliku: " + Files.readAllLines(Path.of(file), StandardCharsets.UTF_8));
    }

    public static String getCityName() {
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
