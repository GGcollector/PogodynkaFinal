package org.example.Data;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonMapper {
    CityForecast mapToForecast(String json) throws JsonProcessingException;
    CityLocalization mapToForecast1(String json) throws JsonProcessingException;
}
