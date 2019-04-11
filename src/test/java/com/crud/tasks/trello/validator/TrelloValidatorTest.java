package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloValidatorTest {
    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        TrelloBoard validTrelloBoard = new TrelloBoard("1", "my_task", new ArrayList<>());
        TrelloBoard unValidTrelloBoard = new TrelloBoard("2", "test", new ArrayList<>());

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(validTrelloBoard);
        trelloBoards.add(unValidTrelloBoard);

        //When
        List<TrelloBoard> trelloBoardsAfterValidation = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotNull(trelloBoardsAfterValidation);
        assertEquals(1, trelloBoardsAfterValidation.size());
        assertTrue(trelloBoardsAfterValidation.contains(validTrelloBoard));
    }


}