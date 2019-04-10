package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    private static final String SUBJECT = "task: new trello card";
    private AdminConfig adminConfig;
    private TrelloClient trelloClient;
    private SimpleEmailService emailService;

    public TrelloService(AdminConfig adminConfig, TrelloClient trelloClient, SimpleEmailService emailService) {
        this.adminConfig = adminConfig;
        this.trelloClient = trelloClient;
        this.emailService = emailService;
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "new card: " + trelloCardDto.getName() + " has been created on your trello account")));
        return newCard;
    }
}
