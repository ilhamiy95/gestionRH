package com.example.parking.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmailWithAttachment(String text, String email, String subject) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
//        helper.setTo(new String[]{"youssef.chater@gmail.com", "abderahman.gym@gmail.com"});

        helper.setSubject(subject);

        // true = text/html
        helper.setText(text, true);

//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);
    }
}
