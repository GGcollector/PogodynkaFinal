package org.example.Logic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherApiClient implements ApiClient {
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String URL_PARAMS = "?q=%s&appid=%s";
    private static final String API_KEY = "9a25cb7505cd23e183fdf38f496c16e3";


    @Override
    public HttpResponse<String> getWeather(final String city) throws IOException, InterruptedException {
        final String apiUrl = String.format(API_URL + URL_PARAMS, city, API_KEY);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        final HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send;
    }
    @Override
    public HttpResponse<String> getWeatherViaScanner(String cityScanner) throws IOException, InterruptedException {
        final String apiUrl = String.format(API_URL + URL_PARAMS, cityScanner, API_KEY);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        final HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send;
    }

//    3. Pokazanie uśrednionej prognozy pogody dla danej lokalizacji z zewnętrznego api
//    1. Użytkownik podaje nazwę jednego z miast dostępnych w pliku
//    2. Jeżeli podane miasto nie występuje w pliku użytkownik ma dostać taką informację
//    3. Wykonywany jest odczyt z 2 zewnętrznych serwisów, który dotyczy:
//    http://api.weatherstack.com/current?access_key=<API_KEY>&query=<city>
//            1. temperature
//        2. ciśnienie
//        3. wilgotność
//        4. kierunek
//        5. prędkośc wiatru
//    4. Oba odczyty są uśredniane
//    5. Tworzony jest obiekt, który zawiera kolejno UUID miast, kraj, data i czas, średnione: temperaturę, ciśnienie, wilgotoność, kierunek i prędkość wiatru
//    6. Obiekt zapisywany jest do innego pliku niż poprzednio - jeżeli wpis dla podanego miasta istnieje to go zastępujemy świeżym
//    7. Informacje są zwracane użytkownikowi
}
