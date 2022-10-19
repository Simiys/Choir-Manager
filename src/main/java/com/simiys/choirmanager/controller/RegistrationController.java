package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnRegistrationDirectorEvent;
import com.simiys.choirmanager.events.OnRegistrationSingerEvent;
import com.simiys.choirmanager.model.*;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.service.UserService;
import com.simiys.choirmanager.model.tables.tokens.DirectorVerificationToken;
import com.simiys.choirmanager.model.tables.tokens.SingerVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration_page";
    }

    @GetMapping("/confirmRegistrationForSinger")
    public String confirmRegistrationForSinger(@RequestParam("token")String token){
        SingerVerificationToken singerVerificationToken = service.getSingerVerificationToken(token);

        if(singerVerificationToken.equals(null)) {
            return "redirect:/alert?type=TI";
        }

        Singer singer = singerVerificationToken.getSinger();
        Calendar calendar = Calendar.getInstance();
        if((singerVerificationToken.getExpireDate().getTime() - calendar.getTime().getTime()) <=0) {
            return "redirect:/alert?type=TE";
        }

        singer.setStatus(Status.ACTIVE);
        singerRepository.save(singer);

        return "redirect:/alert?type=REG_C";
    }

    @GetMapping("/confirmRegistrationForDirector")
    public String confirmRegistrationForDirector(@RequestParam("token") String token) {
        DirectorVerificationToken verificationToken = service.getDirectorVerificationToken(token);

        if(verificationToken.equals(null)) {
            return "redirect:/alert?type=TI";
        }

        ChoirDirector director = verificationToken.getDirector();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpireDate().getTime() - calendar.getTime().getTime()) <= 0) {
            return "redirect:/alert?type=TE";
        }

        director.setStatus(Status.ACTIVE);
        directorRepository.save(director);
        return "redirect:/alert?type=REG_C";
    }

    @GetMapping("/regSuccess")
    public String regSuccess() {
         return "redirect:/alert?type=REG_MM";
    }

    @PostMapping("/sendRegEmail")
    public void resendRegEmail(@RequestBody String email) {
        if (directorRepository.findByEmail(email).isPresent()) {
            ChoirDirector director = directorRepository.findByEmail(email).get();
            eventPublisher.publishEvent(new OnRegistrationDirectorEvent(director));
        } else {
            eventPublisher.publishEvent(new OnRegistrationSingerEvent(singerRepository.findByEmail(email).get()));
        }
    }
}
