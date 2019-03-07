package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        for (TrelloBoardDto trelloBoardDto : trelloBoards) {
            if (isKodillaBoard(trelloBoardDto)) {
                System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
            }
        }

    }

    private boolean isKodillaBoard(TrelloBoardDto trelloBoardDto) {
        if (trelloBoardDto.getId() == null) return false;
        if (trelloBoardDto.getName() == null) return false;
        return trelloBoardDto.getName().contains("Kodilla");
    }
}