package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test task", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc.perform(get("/v1/trello/boards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //or is(200)
                // Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("test task")))
                // Trello list fields
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("test list")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));
    }

    @Test
    public void shouldCreateTrelloCard() throws Exception {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "test", "test description", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "323", "test", "http://test.com", new BadgesDto());

        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        //When & Then
        mockMvc.perform(post("/v1/trello/card")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("323")))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.shortUrl", is("http://test.com")));
    }
}