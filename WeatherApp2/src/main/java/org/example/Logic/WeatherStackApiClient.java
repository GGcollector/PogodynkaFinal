package org.example.Logic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherStackApiClient implements ApiClient{
    private static final String API_URL = "http://api.weatherstack.com/current";
    private static final String URL_PARAMS = "?access_key=%s&query=%s";
    private static final String API_KEY = "c6c47bac6b970633875709482e6e7555";
    @Override
    public HttpResponse<String> getWeather(final String city) throws IOException, InterruptedException {
        final String apiUrl = String.format(API_URL + URL_PARAMS, API_KEY, city);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        final HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send;
    }
    @Override
    public HttpResponse<String> getWeatherViaScanner(String cityScanner) throws IOException, InterruptedException {
        final String apiUrl = String.format(API_URL + URL_PARAMS, API_KEY, cityScanner);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        final HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return send;
    }
}
