package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorPassRecRepository;
import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerPassRecRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnDirectorPasswordRecoveryEvent;
import com.simiys.choirmanager.events.OnSingerPasswordRecoveryEvent;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.model.UserDTO;
import com.simiys.choirmanager.model.tables.tokens.DirectorPasswordRecoveryToken;
import com.simiys.choirmanager.model.tables.tokens.SingerPasswordRecoveryToken;
import com.simiys.choirmanager.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Controller
@RequestMapping("/passrec")
public class PasswordRecoveryController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    PasswordRecoveryService service;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    SingerPassRecRepository singerPassRecRepository;

    @Autowired
    DirectorPassRecRepository directorPassRecRepository;

    @GetMapping("/start")
    public String startRecovery() {
        return "recoveryRequestPage";
    }

    @PostMapping("/request")
    @ResponseBody
    public String recoveryRequest(@RequestBody UserDTO dto) {
        if (directorRepository.findByEmail(dto.getUsername()).isPresent()) {
            ChoirDirector director = directorRepository.findByEmail(dto.getUsername()).get();
            eventPublisher.publishEvent(new OnDirectorPasswordRecoveryEvent(director));
            return "RECOVERY_MM";
        } else if (singerRepository.findByEmail(dto.getUsername()).isPresent()){
            Singer singer = singerRepository.findByEmail(dto.getUsername()).get();
            eventPublisher.publishEvent(new OnSingerPasswordRecoveryEvent(singer));
            return "RECOVERY_MM";
        }
        return "NSUP";
    }

    @GetMapping("/forSinger")
    public String passwordRecoveryForSinger(@RequestParam("token")String token, HttpServletResponse response) {

        if (token.equals(null) || singerPassRecRepository.findByToken(token).isEmpty()) {
            return "redirect:/alert?type=TI";
        }
        SingerPasswordRecoveryToken recoveryToken = service.findSingerToken(token);

        Calendar calendar = Calendar.getInstance();
        if(recoveryToken.getExpireDate().getTime() - calendar.getTime().getTime() <= 0) {
            return "redirect:/alert?type=TE";
        }
        Cookie cookie = new Cookie("RECOVERYTOKEN", token);
        cookie.setPath("/passrec/");
        response.addCookie(cookie);

        return "singerRecoveryPage";
    }

    @GetMapping("/forDirector")
    public String passwordRecoveryForDirector(@RequestParam("token")String token, HttpServletResponse response) {

        if (token.equals(null) || directorPassRecRepository.findByToken(token).isEmpty()) {
            return "redirect:/alert?type=TI";
        }

        DirectorPasswordRecoveryToken recoveryToken = service.findDirectorToken(token);

        Calendar calendar = Calendar.getInstance();
        if(recoveryToken.getExpireDate().getTime() - calendar.getTime().getTime() <= 0) {
            return "redirect:/alert?type=TE";
        }
        Cookie cookie = new Cookie("RECOVERYTOKEN", token);
        cookie.setPath("/passrec/");
        response.addCookie(cookie);

        return "directorRecoveryPage";

    }

    @PostMapping("/singerRecover")
    @ResponseBody
    public String overwriteSingerPassword(@RequestBody String password, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        SingerPasswordRecoveryToken token = new SingerPasswordRecoveryToken();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("RECOVERYTOKEN")) {
                    System.out.println("cookie find!");
                    token = singerPassRecRepository.findByToken(cookie.getValue()).orElseThrow();
                }
            }

        } else {
            return "SW";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        Singer singer = token.getSinger();
        String newPassword = encoder.encode(password);
        singer.setPassword(newPassword);
        singerRepository.save(singer);

        return "RECOVERY_C";

    }

    @PostMapping("/directorRecover")
    @ResponseBody
    public String overwriteDirectorPassword(@RequestBody String password, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        DirectorPasswordRecoveryToken token = new DirectorPasswordRecoveryToken();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("RECOVERYTOKEN")) {
                    token = directorPassRecRepository.findByToken(cookie.getValue()).orElseThrow();
                }
            }

        } else {
            return "SW";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        ChoirDirector director = token.getDirector();
        String newPassword = encoder.encode(password);
        director.setPassword(newPassword);
        directorRepository.save(director);

        return "RECOVERY_C";

    }
}
