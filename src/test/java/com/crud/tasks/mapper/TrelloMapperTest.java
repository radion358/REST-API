package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTest {
    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoards() {
        //Given
        TrelloList trelloList0 = new TrelloList("test id 0", "test name 0", false);
        TrelloList trelloList1 = new TrelloList("test id 1", "test name 1", true);
        TrelloList trelloList2 = new TrelloList("test id 2", "test name 2", false);
        TrelloList trelloList3 = new TrelloList("test id 3", "test name 3", false);
        TrelloList trelloList4 = new TrelloList("test id 4", "test name 4", true);
        TrelloList trelloList5 = new TrelloList("test id 5", "test name 5", false);

        List<TrelloList> trelloLists0 = new ArrayList<>();
        trelloLists0.add(trelloList0);
        trelloLists0.add(trelloList1);
        trelloLists0.add(trelloList2);
        List<TrelloList> trelloLists1 = new ArrayList<>();
        trelloLists1.add(trelloList3);
        trelloLists1.add(trelloList4);
        trelloLists1.add(trelloList5);

        TrelloBoard trelloBoard0 = new TrelloBoard(
                "test id 0", "test name 0", trelloLists0);
        TrelloBoard trelloBoard1 = new TrelloBoard(
                "test id 1", "test name 1", trelloLists1);

        List<TrelloBoard> expectedTrelloBoards = new ArrayList<>();
        expectedTrelloBoards.add(trelloBoard0);
        expectedTrelloBoards.add(trelloBoard1);

        TrelloListDto trelloListDto0 = new TrelloListDto("test id 0", "test name 0", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("test id 1", "test name 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("test id 2", "test name 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("test id 3", "test name 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("test id 4", "test name 4", true);
        TrelloListDto trelloListDto5 = new TrelloListDto("test id 5", "test name 5", false);

        List<TrelloListDto> trelloListDtos0 = new ArrayList<>();
        trelloListDtos0.add(trelloListDto0);
        trelloListDtos0.add(trelloListDto1);
        trelloListDtos0.add(trelloListDto2);
        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        trelloListDtos1.add(trelloListDto3);
        trelloListDtos1.add(trelloListDto4);
        trelloListDtos1.add(trelloListDto5);

        TrelloBoardDto trelloBoardDto0 = new TrelloBoardDto(
                "test id 0", "test name 0", trelloListDtos0);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto(
                "test id 1", "test name 1", trelloListDtos1);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto0);
        trelloBoardDtos.add(trelloBoardDto1);

        //When
        List<TrelloBoard> actualTrelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(2, actualTrelloBoards.size());
        assertEquals(expectedTrelloBoards, actualTrelloBoards);
    }

    @Test
    public void mapToList() {
        //Given
        TrelloList trelloList0 = new TrelloList("test id 0", "test name 0", false);
        TrelloList trelloList1 = new TrelloList("test id 1", "test name 1", true);
        TrelloList trelloList2 = new TrelloList("test id 2", "test name 2", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList0);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        TrelloListDto trelloListDto0 = new TrelloListDto("test id", "test name 0", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("test id", "test name 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("test id", "test name 2", false);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto0);
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        //When
        List<TrelloList> actualTrelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(3, actualTrelloLists.size());
        for (int i = 0; i > actualTrelloLists.size(); i++) {
            assertEquals(actualTrelloLists.get(i), trelloLists.get(i));
        }
    }

    @Test
    public void mapToBoardDtos() {
        //Given
        TrelloList trelloList0 = new TrelloList("test id 0", "test name 0", false);
        TrelloList trelloList1 = new TrelloList("test id 1", "test name 1", true);
        TrelloList trelloList2 = new TrelloList("test id 2", "test name 2", false);
        TrelloList trelloList3 = new TrelloList("test id 3", "test name 3", false);
        TrelloList trelloList4 = new TrelloList("test id 4", "test name 4", true);
        TrelloList trelloList5 = new TrelloList("test id 5", "test name 5", false);

        List<TrelloList> trelloLists0 = new ArrayList<>();
        trelloLists0.add(trelloList0);
        trelloLists0.add(trelloList1);
        trelloLists0.add(trelloList2);
        List<TrelloList> trelloLists1 = new ArrayList<>();
        trelloLists1.add(trelloList3);
        trelloLists1.add(trelloList4);
        trelloLists1.add(trelloList5);

        TrelloBoard trelloBoard0 = new TrelloBoard(
                "test id 0", "test name 0", trelloLists0);
        TrelloBoard trelloBoard1 = new TrelloBoard(
                "test id 1", "test name 1", trelloLists1);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard0);
        trelloBoards.add(trelloBoard1);

        TrelloListDto trelloListDto0 = new TrelloListDto("test id 0", "test name 0", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("test id 1", "test name 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("test id 2", "test name 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("test id 3", "test name 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("test id 4", "test name 4", true);
        TrelloListDto trelloListDto5 = new TrelloListDto("test id 5", "test name 5", false);

        List<TrelloListDto> trelloListDtos0 = new ArrayList<>();
        trelloListDtos0.add(trelloListDto0);
        trelloListDtos0.add(trelloListDto1);
        trelloListDtos0.add(trelloListDto2);
        List<TrelloListDto> trelloListDtos1 = new ArrayList<>();
        trelloListDtos1.add(trelloListDto3);
        trelloListDtos1.add(trelloListDto4);
        trelloListDtos1.add(trelloListDto5);

        TrelloBoardDto trelloBoardDto0 = new TrelloBoardDto(
                "test id 0", "test name 0", trelloListDtos0);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto(
                "test id 1", "test name 1", trelloListDtos1);

        List<TrelloBoardDto> expectedTrelloBoardDtos = new ArrayList<>();
        expectedTrelloBoardDtos.add(trelloBoardDto0);
        expectedTrelloBoardDtos.add(trelloBoardDto1);

        //When
        List<TrelloBoardDto> actualTrelloBoardDtos = trelloMapper.mapToBoardDtos(trelloBoards);

        //Then
        assertEquals(2, actualTrelloBoardDtos.size());
        assertEquals(expectedTrelloBoardDtos, actualTrelloBoardDtos);
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList0 = new TrelloList("test id 0", "test name 0", false);
        TrelloList trelloList1 = new TrelloList("test id 1", "test name 1", true);
        TrelloList trelloList2 = new TrelloList("test id 2", "test name 2", false);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList0);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        TrelloListDto trelloListDto0 = new TrelloListDto("test id", "test name 0", false);
        TrelloListDto trelloListDto1 = new TrelloListDto("test id", "test name 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("test id", "test name 2", false);

        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto0);
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        //When
        List<TrelloListDto> actualTrelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, actualTrelloListDtos.size());
        for (int i = 0; i > actualTrelloListDtos.size(); i++) {
            assertEquals(actualTrelloListDtos.get(i), trelloListDtos.get(i));
        }
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCard expectedTrelloCard = new TrelloCard(
                "test name", "test description", "test pos", "test list id");
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "test name", "test description", "test pos", "test list id");

        //When
        TrelloCard actualTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(expectedTrelloCard, actualTrelloCard);
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCardDto expectedTrelloCardDto = new TrelloCardDto(
                "test name", "test description", "test pos", "test list id");
        TrelloCard trelloCard = new TrelloCard(
                "test name", "test description", "test pos", "test list id");

        //When
        TrelloCardDto actualTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(expectedTrelloCardDto, actualTrelloCardDto);
    }
}