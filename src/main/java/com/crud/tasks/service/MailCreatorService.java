package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    CompanyConfig companyConfig;
    private AdminConfig adminConfig;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public MailCreatorService(CompanyConfig companyConfig, AdminConfig adminConfig, TemplateEngine templateEngine) {
        this.companyConfig = companyConfig;
        this.adminConfig = adminConfig;
        this.templateEngine = templateEngine;
    }

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Providers connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://radoslaw-dauksza.github.io/radoslaw.dauksza.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_config", companyConfig);
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("preview_message", message.substring(0, message.length() / 3));
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildSheduledInformationEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Providers connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://radoslaw-dauksza.github.io/radoslaw.dauksza.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("company_config", companyConfig);
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("preview_message", message.substring(0, message.length() / 3));
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
