package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnDirectorPasswordRecoveryEvent;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.service.PasswordRecoveryService;
import lombok.SneakyThrows;
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

    @SneakyThrows
    private void recoverPassword(OnDirectorPasswordRecoveryEvent event) {
        ChoirDirector director = event.getDirector();
        String token = UUID.randomUUID().toString();
        service.createDirectorPassRecovToken(director, token);

        String from = "ChorusManager1@yandex.ru";
        String address = director.getEmail();
        String subject = "Восстановление пароля Choir Manager";
        String url = "https://chorusmanager.herokuapp.com/passrec/forDirector?token=" + token;
        String message = "Для восстановления пароля перейдите по ссылке ниже. \n Ссылка действительна в течении 15 минут. \r\n  \n" + url +" \r\n \n Спасибо, что пользуетесь нашим приложением";


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(address);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
        mailSender.send(mailMessage);

    }
}
