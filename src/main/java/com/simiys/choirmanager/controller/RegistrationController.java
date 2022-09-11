package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.*;
import com.simiys.choirmanager.service.UserService;
import com.simiys.choirmanager.model.tokens.DirectorVerificationToken;
import com.simiys.choirmanager.model.tokens.SingerVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration_page";
    }

    @GetMapping("/confirmRegistrationForSinger")
    public String confirmRegistrationForSinger(@RequestParam("token")String token, Model model){
        SingerVerificationToken singerVerificationToken = service.getSingerVerificationToken(token);

        if(singerVerificationToken.equals(null)) {
            return "redirect:/passrec/alert?type=TI";
        }

        Singer singer = singerVerificationToken.getSinger();
        Calendar calendar = Calendar.getInstance();
        if((singerVerificationToken.getExpireDate().getTime() - calendar.getTime().getTime()) <=0) {
            return "redirect:/passrec/alert?type=TE";
        }

        singer.setStatus(Status.ACTIVE);
        singerRepository.save(singer);

        return "redirect:/passrec/alert?type=REG_C";
    }

    @GetMapping("/confirmRegistrationForDirector")
    public String confirmRegistrationForDirector(@RequestParam("token") String token) {
        DirectorVerificationToken verificationToken = service.getDirectorVerificationToken(token);

        if(verificationToken.equals(null)) {
            return "redirect:/passrec/alert?type=TI";
        }

        ChoirDirector director = verificationToken.getDirector();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpireDate().getTime() - calendar.getTime().getTime()) <= 0) {
            return "redirect:/passrec/alert?type=TE";
        }

        director.setStatus(Status.ACTIVE);
        directorRepository.save(director);
        return "redirect:/passrec/alert?type=REG_C";
    }

    @GetMapping("/regSuccess")
    public String regSuccess(Model model) {
         return "redirect:/passrec/alert?type=REG_MM";
    }
}
