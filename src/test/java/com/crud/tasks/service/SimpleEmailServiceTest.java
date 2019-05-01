package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailCreatorService mailCreatorService;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test", "test", "Test message");

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getReceiverEmail());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());

        MimeMessagePreparator mailMessage = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getReceiverEmail());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };

        //When
        simpleEmailService.send(mail);

        //Then
//        verify(javaMailSender).send(mailMessage);
    }

}