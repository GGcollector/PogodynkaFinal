package org.example.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CityLocalizationMapper implements JsonMapper {

    @Override
    public CityLocalization mapToForecast1(final String json) throws JsonProcessingException {

        ObjectMapper jsonMapper = new ObjectMapper();
        final JsonNode jsonNode = jsonMapper.readTree(json);

        CityLocalization forecast = new CityLocalization(
                jsonNode.get("location").get("name").asText(),
                jsonNode.get("location").get("country").asText(),
                jsonNode.get("location").get("lat").asText(),
                jsonNode.get("location").get("lon").asText());

        return forecast;
    }

    @Override
    public CityForecast mapToForecast(String json) throws JsonProcessingException {
        return null;
    }
}

