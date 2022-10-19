package com.simiys.choirmanager.listeners;

import com.simiys.choirmanager.events.OnSingerJoinEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SingerJoinListener implements ApplicationListener<OnSingerJoinEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnSingerJoinEvent event) {
        this.singerJoinRequest(event);
    }

    private void singerJoinRequest (OnSingerJoinEvent event) {
        String from = "ChorusManager1@yandex.ru";
        String address = event.getDirectorEmail();
        String subject = "Присоединение певца";
        String url = "/api/joinSinger?id=" + event.getId();
        String message = "К вашему хору хочет присоедениться " + event.getName() + "(" + event.getSingerEmail() + "). \r Для присоединения перейдите по ссылке ниже:";


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(address);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\r\n" + "http://localhost:8080" + url + "\n С уважением Chorus Manager");
        mailSender.send(mailMessage);

    }
}
