package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnRegistrationDirectorEvent;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DirectorRegistrationListener implements ApplicationListener<OnRegistrationDirectorEvent> {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService service;

    @Override
    public void onApplicationEvent(OnRegistrationDirectorEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationDirectorEvent event){
        ChoirDirector director = event.getDirector();
        String token = UUID.randomUUID().toString();
        service.createDirectorToken(token, director);

        String from = "ChoirManager1@yandex.ru";
        String address = director.getEmail();
        String subject = "Подтверждение регистрации Choir Manager";
        String url = event.getAppUrl() + "/confirmRegistrationForDirector?token=" + token;
        String message = "Спасибо, что пользуетесь нашим приложением! \r Для завершения регистрации перейдите по этой ссылке";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(address);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\r\n" + "http://localhost:8080" + url);
        mailSender.send(mailMessage);


    }


}
