package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnSingerPasswordRecoveryEvent;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SingerPasswordRecoveryListener implements ApplicationListener<OnSingerPasswordRecoveryEvent> {

    @Autowired
    PasswordRecoveryService service;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnSingerPasswordRecoveryEvent event) {
        this.recoverPassword(event);
    }

    private void recoverPassword(OnSingerPasswordRecoveryEvent event) {
        Singer singer = event.getSinger();
        String token = UUID.randomUUID().toString();
        service.createSingerPassRecovToken(singer, token);

        String from = "ChorusManager1@yandex.ru";
        String address = singer.getEmail();
        String subject = "Восстановление пароля Choir Manager";
        String url = "https://chorusmanager.herokuapp.com/passrec/forSinger?token=" + token;
        String message = "Для восстановления пароля перейдите по ссылке ниже. \n Ссылка действительна в течении 15 минут. \r\n " + url +" \r\n Спасибо, что пользуетесь нашим приложением";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(address);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
