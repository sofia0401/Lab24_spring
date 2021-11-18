package com.example.demo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void MailSender(Object o) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("testmailbox411@gmail.com");
        message.setFrom("testmailbox411@gmail.com");
        message.setSubject("Info about saved object");
        message.setText("Object " + o.toString() + " was successfully added");

        javaMailSender.send(message);
    }
}





