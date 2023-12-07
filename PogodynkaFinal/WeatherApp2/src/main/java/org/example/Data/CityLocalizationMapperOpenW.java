package org.example.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CityLocalizationMapperOpenW implements JsonMapper {
    @Override
    public CityForecast mapToForecast(String json) throws JsonProcessingException {
        return null;
    }

    @Override
    public CityLocalization mapToForecast1(String json) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        final JsonNode jsonNode = jsonMapper.readTree(json);

        CityLocalization forecast = new CityLocalization(
                jsonNode.get("name").asText(),
                jsonNode.get("sys").get("country").asText(),
                jsonNode.get("coord").get("lat").asText(),
                jsonNode.get("coord").get("lon").asText());

        return forecast;
    }
}

