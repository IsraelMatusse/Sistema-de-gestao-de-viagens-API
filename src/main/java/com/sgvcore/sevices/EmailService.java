package com.sgvcore.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService extends Thread {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        if (emailSender == null) {
            throw new IllegalStateException("Email sender is not initialized");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("israelmathusse451@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }}
