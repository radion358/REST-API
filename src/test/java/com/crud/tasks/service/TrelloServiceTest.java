package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void fetchTrelloBoards() {
        //When
        trelloService.fetchTrelloBoards();

        // Then
        verify(trelloClient, only()).getTrelloBoards();
    }

    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "trello_card", "my_trello_card", "pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto());

        //When
        trelloService.createdTrelloCard(trelloCardDto);

        //Then
        verify(trelloClient, only()).createNewCard(trelloCardDto);
        verify(simpleEmailService, only()).send(any());
    }
}