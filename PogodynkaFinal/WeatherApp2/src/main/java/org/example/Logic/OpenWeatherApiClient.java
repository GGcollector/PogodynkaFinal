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
    private static final String URL_PARAMS_COORD = "?lat=%s&lon=%s&appid=%s";


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
    @Override
    public HttpResponse<String> getWeatherThroughCoords(float lat, float lon) throws IOException, InterruptedException {
        final String apiUrl = String.format(API_URL + URL_PARAMS_COORD, lat, lon, API_KEY);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        final HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send;
    }

}
