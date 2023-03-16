package com.smirnov.technicaltest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smirnov.technicaltest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersSearchService {
    private static final String BASE_URL = "https://api.github.com";
    @Autowired
    private RestTemplate restTemplate;

    public List<UserDto> getUsers(String query, String order, int perPage) throws JsonProcessingException {
        String url = BASE_URL + "/search/repositories?q=" + query +
                "&order=" + order + "&per_page=" + perPage;
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        ArrayNode items = (ArrayNode) root.get("items");
        List<UserDto> userDtos = new ArrayList<>();

        for (JsonNode item : items) {
            String login = item.get("owner").get("login").asText();
            String userUrl = BASE_URL + "/users/" + login;
            ResponseEntity<String> userResponse = restTemplate.getForEntity(userUrl, String.class);
            JsonNode userRoot = mapper.readTree(userResponse.getBody());
            UserDto userDTO = mapper.treeToValue(userRoot, UserDto.class);
            userDtos.add(userDTO);
        }

        return userDtos;
    }
}
