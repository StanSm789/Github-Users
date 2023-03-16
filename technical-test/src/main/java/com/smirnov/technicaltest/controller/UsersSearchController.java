package com.smirnov.technicaltest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smirnov.technicaltest.dto.UserDto;
import com.smirnov.technicaltest.service.UsersSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UsersSearchController {
    @Autowired
    private UsersSearchService searchService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam("q") String query,
                                                  @RequestParam("order") String order,
                                                  @RequestParam("per_page") int perPage) throws JsonProcessingException {
        return ResponseEntity.ok(searchService.getUsers(query, order, perPage));
    }
}
