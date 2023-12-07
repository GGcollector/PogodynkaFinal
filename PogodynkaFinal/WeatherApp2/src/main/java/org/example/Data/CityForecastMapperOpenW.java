package org.example.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CityForecastMapperOpenW implements JsonMapper {

    public CityForecast mapToForecast(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode2 = objectMapper.readTree(json);

        CityForecast forecast = new CityForecast(
                jsonNode2.get("name").asText(),
                jsonNode2.get("sys").get("country").asText(),
                jsonNode2.get("main").get("temp").asInt(),
                jsonNode2.get("main").get("pressure").asInt(),
                jsonNode2.get("main").get("humidity").asInt(),
                jsonNode2.get("wind").get("deg").asInt(),
                jsonNode2.get("wind").get("speed").asInt());
        return forecast;
    }

    @Override
    public CityLocalization mapToForecast1(String json) throws JsonProcessingException {
        return null;
    }

}
