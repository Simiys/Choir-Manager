package com.simiys.choirmanager.rest;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnRegistrationDirectorEvent;
import com.simiys.choirmanager.events.OnRegistrationSingerEvent;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.UserForRegistration;
import com.simiys.choirmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;


import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
public class RegistrationRestController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserService service;

    @Autowired
    DirectorRepository directorRepository;

//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/registr")
    public String registrUser(@RequestBody UserForRegistration user, HttpServletRequest request){
        try {
            if (!user.allFieldsFilled()) {
                return "fillException";
            }
        } catch (IllegalAccessException e) {
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        }
        if(!user.validEmail()) {
            return "emailError";
        }
        if(!user.validPassword()) {
            return "passwordError";
        }

        boolean isPresent = directorRepository.findByEmail(user.getEmail()).isPresent() || singerRepository.findByEmail(user.getEmail()).isPresent();
        if(isPresent){
            return "userIsPresent";
        }

        if(user.isRegent()) {
            System.out.println(user.getPassword() + " RRC 61 director password");
            ChoirDirector director =  service.registrRegent(user);
            eventPublisher.publishEvent(new OnRegistrationDirectorEvent(director, request.getLocale(), request.getContextPath()));
        } else {
            Singer singer = service.registrSinger(user);
            eventPublisher.publishEvent(new OnRegistrationSingerEvent(singer, request.getLocale(), request.getContextPath()));
        }
        return "success";
    }
}
