package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnDirectorPasswordRecoveryEvent;
import com.simiys.choirmanager.events.OnSingerPasswordRecoveryEvent;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DirectorPasswordRecoveryListener implements ApplicationListener<OnDirectorPasswordRecoveryEvent> {

    @Autowired
    PasswordRecoveryService service;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnDirectorPasswordRecoveryEvent event) {
        this.recoverPassword(event);
    }

    private void recoverPassword(OnDirectorPasswordRecoveryEvent event) {
        ChoirDirector director = event.getDirector();
        String token = UUID.randomUUID().toString();
        service.createDirectorPassRecovToken(director, token);

        String from = "ChoirManager1@yandex.ru";
        String address = director.getEmail();
        String subject = "Восстановление пароля Choir Manager";
        String url = "http://localhost:8080/passrec/forDirector?token=" + token;
        String message = "Для восстановления пароля перейдите по ссылке ниже. \n Ссылка действительна в течении 15 минут. \r\n " + url +" \r\n C уважение команда разработки Choir Manager!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(address);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
