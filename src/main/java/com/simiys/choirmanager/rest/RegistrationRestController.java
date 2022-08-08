package com.simiys.choirmanager.rest;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.Status;
import com.simiys.choirmanager.model.UserForRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RequestMapping("/api")
@RestController
public class RegistrationRestController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorRepository directorRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/registr")
    public String registrUser(@RequestBody UserForRegistration user){
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

        boolean isPresent;
        if(user.isRegent()) {
            isPresent = directorRepository.findByEmail(user.getEmail()).isPresent();
        } else {
            isPresent = singerRepository.findByEmail(user.getEmail()).isPresent();
        }

        if(isPresent){
            return "userIsPresent";
        }

        if(user.isRegent()) {
            ChoirDirector director = new ChoirDirector(
                    user.getEmail(),encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
            director.setStatus(Status.BANNED);
            directorRepository.save(director);
        } else {
            Singer singer = new Singer(
                    user.getEmail(), encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
            singer.setStatus(Status.BANNED);
            singerRepository.save(singer);
        }
        return "success";
    }
}
