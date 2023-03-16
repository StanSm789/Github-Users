package com.smirnov.technicaltest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smirnov.technicaltest.service.UsersSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersSearchServiceTests {
    @Autowired
    private UsersSearchService usersSearchService;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetUsers() throws Exception {
        String query = "postman";
        String order = "desc";
        int perPage = 1;
        String searchUrl = "https://api.github.com/search/repositories?q=" + query + "&order=" + order + "&per_page=" + perPage;
        String userUrl = "https://api.github.com/users/hlmd";

        mockServer.expect(requestTo(searchUrl))
                .andRespond(withSuccess("{\"items\":[{\"owner\":{\"login\":\"hlmd\"}}]}", MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(userUrl))
                .andRespond(withSuccess("{\"login\":\"hlmd\"", MediaType.APPLICATION_JSON));

        String expectedResult = "hlmd";
        String actualResult = usersSearchService.getUsers(query, order, perPage).get(0).getLogin();

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
