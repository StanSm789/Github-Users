package com.smirnov.technicaltest.controller;

import com.smirnov.technicaltest.dto.UserDto;
import com.smirnov.technicaltest.service.UsersSearchService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UsersSearchControllerTest {
    @Mock
    private UsersSearchService searchService;
    @InjectMocks
    private UsersSearchController controller;

    @Test
    public void testGetUsers() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setLogin("user1");
        userDto.setId(1);
        userDto.setNodeId("node1");
        userDto.setAvatarUrl("url1");
        userDto.setGravatarId("gravatar1");
        userDto.setUrl("url1");
        userDto.setHtmlUrl("htmlUrl1");
        userDto.setFollowersUrl("followersUrl1");
        userDto.setFollowingUrl("followingUrl1");
        userDto.setGistsUrl("gistsUrl1");
        userDto.setStarredUrl("starredUrl1");

        List<UserDto> userDtos = Arrays.asList(userDto);
        Mockito.when(searchService.getUsers("query", "order", 1)).thenReturn(userDtos);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .param("q", "query")
                        .param("order", "order")
                        .param("per_page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].login", Matchers.is("user1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nodeId", Matchers.is("node1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].avatarUrl", Matchers.is("url1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gravatarId", Matchers.is("gravatar1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].url", Matchers.is("url1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].htmlUrl", Matchers.is("htmlUrl1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].followersUrl", Matchers.is("followersUrl1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].followingUrl", Matchers.is("followingUrl1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gistsUrl", Matchers.is("gistsUrl1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].starredUrl", Matchers.is("starredUrl1")));
    }
}

