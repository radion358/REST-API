package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private SimpleEmailService simpleEmailService;
    private TaskRepository taskRepository;
    private AdminConfig adminConfig;
    private static final String SUBJECT = "tasks: once a day email";

    public EmailScheduler(SimpleEmailService simpleEmailService,
                          TaskRepository taskRepository, AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }


    @Scheduled(cron = "0 0 16 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message ="currently in database you got: " + size + " task";
        if (size != 1) {
            message += "s";
        }
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message
        ));
    }
}
