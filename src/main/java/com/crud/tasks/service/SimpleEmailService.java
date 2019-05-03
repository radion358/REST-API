package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    private JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;

    public SimpleEmailService(JavaMailSender javaMailSender, MailCreatorService mailCreatorService) {
        this.javaMailSender = javaMailSender;
        this.mailCreatorService = mailCreatorService;
    }

    public void send(final Mail mail) {
        LOGGER.info("starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("email has been sent.");
        } catch (MailException e) {
            LOGGER.error("filed to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getReceiverEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc() != null && !mail.getToCc().isEmpty()) {
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage( Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getReceiverEmail());
            messageHelper.setSubject(mail.getSubject());
            if (mail.getMessage().contains("currently in database you got:")) {
                messageHelper.setText(mailCreatorService.buildSheduledInformationEmail(mail.getMessage()), true);
            } else {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
        };

    }
}
