package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://radoslaw-dauksza.github.io/radoslaw.dauksza.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig.getCompanyName() + " "
                + companyConfig.getCompanyGoal() + " "
                + companyConfig.getCompanyEmail() + " "
                + companyConfig.getCompanyPhone());
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("preview_message", message.substring(0, 50));
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
