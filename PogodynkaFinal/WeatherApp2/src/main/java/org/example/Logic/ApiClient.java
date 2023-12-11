package org.example.Logic;



import java.io.IOException;
import java.net.http.HttpResponse;

public interface ApiClient {
    HttpResponse<String> getWeather(String city) throws IOException, InterruptedException;

    HttpResponse<String> getWeatherViaScanner (String cityScanner) throws IOException, InterruptedException;
    HttpResponse<String> getWeatherThroughCoords (float lat, float lon) throws IOException, InterruptedException;




}
