package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private String receiverEmail;
    private String subject;
    private String message;
    private String toCc;

    public Mail(String receiverEmail, String subject, String message) {
        this.receiverEmail = receiverEmail;
        this.subject = subject;
        this.message = message;
    }
}
