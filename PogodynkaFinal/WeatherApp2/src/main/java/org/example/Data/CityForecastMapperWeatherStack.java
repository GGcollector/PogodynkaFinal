package org.example.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CityForecastMapperWeatherStack implements JsonMapper {

    @Override
    public CityForecast mapToForecast(final String json) throws JsonProcessingException {

            ObjectMapper jsonMapper = new ObjectMapper();
            final JsonNode jsonNode = jsonMapper.readTree(json);

            CityForecast forecast = new CityForecast(
                    jsonNode.get("location").get("name").asText(),
                    jsonNode.get("location").get("country").asText(),
                    jsonNode.get("current").get("temperature").asInt(),
                    jsonNode.get("current").get("pressure").asInt(),
                    jsonNode.get("current").get("humidity").asInt(),
                    jsonNode.get("current").get("wind_dir").asText(),
                    jsonNode.get("current").get("wind_speed").asInt());

        return forecast;
    }

    @Override
    public CityLocalization mapToForecast1(String json) throws JsonProcessingException {
        return null;
    }
}