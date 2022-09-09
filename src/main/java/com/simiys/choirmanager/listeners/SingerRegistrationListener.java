package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnRegistrationSingerEvent;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SingerRegistrationListener implements ApplicationListener<OnRegistrationSingerEvent> {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService service;

    @Override
    public void onApplicationEvent(OnRegistrationSingerEvent event){
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSingerEvent event) {
        Singer singer = event.getSinger();
        String token = UUID.randomUUID().toString();
        service.createSingerToken(token, singer);

        String from = "ChoirManager1@yandex.ru";
        String address = singer.getEmail();
        String subject = "Подтверждение регистрации Choir Manager";
        String url = event.getAppUrl() + "/confirmRegistrationForSinger?token=" + token;
        String message = "Спасибо, что пользуетесь нашим приложением! \r Для завершения регистрации перейдите по этой ссылке";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(address);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + url);
        mailSender.send(email);


    }


}
