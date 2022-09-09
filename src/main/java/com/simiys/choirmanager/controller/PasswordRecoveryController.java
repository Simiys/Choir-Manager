package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorPassRecRepository;
import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerPassRecRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnDirectorPasswordRecoveryEvent;
import com.simiys.choirmanager.events.OnSingerPasswordRecoveryEvent;
import com.simiys.choirmanager.model.AlertMessages;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.UserDTO;
import com.simiys.choirmanager.model.tokens.DirectorPasswordRecoveryToken;
import com.simiys.choirmanager.model.tokens.SingerPasswordRecoveryToken;
import com.simiys.choirmanager.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        System.out.println("PRC 78 started");
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
            return "redirect:/passrec/alert?type=TI";
        }
        SingerPasswordRecoveryToken recoveryToken = service.findSingerToken(token);

        Calendar calendar = Calendar.getInstance();
        if(recoveryToken.getExpireDate().getTime() - calendar.getTime().getTime() <= 0) {
            return "redirect:/passrec/alert?type=TE";
        }
        System.out.println("PRC 83 creating token");
        Cookie cookie = new Cookie("RECOVERYTOKEN", token);
        cookie.setPath("/passrec/");
        response.addCookie(cookie);

        return "singerRecoveryPage";
    }

    @GetMapping("/forDirector")
    public String passwordRecoveryForDirector(@RequestParam("token")String token, HttpServletResponse response) {

        if (token.equals(null) || directorPassRecRepository.findByToken(token).isEmpty()) {
            return "redirect:/passrec/alert?type=TI";
        }

        DirectorPasswordRecoveryToken recoveryToken = service.findDirectorToken(token);

        Calendar calendar = Calendar.getInstance();
        if(recoveryToken.getExpireDate().getTime() - calendar.getTime().getTime() <= 0) {
            return "redirect:/passrec/alert?type=TE";
        }
        System.out.println("PRC 104 creating cookie");
        Cookie cookie = new Cookie("RECOVERYTOKEN", token);
        cookie.setPath("/passrec/");
        response.addCookie(cookie);

        return "directorRecoveryPage";

    }

    @PostMapping("/singerRecover")
    @ResponseBody
    public String overwriteSingerPassword(@RequestBody String password, HttpServletRequest request) {
        System.out.println("PRC 115 " + password);
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
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
    public String overwriteDirectorPassword(@RequestBody String password, HttpServletRequest request, Model model) {
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

    @GetMapping("/alert")
    public String method(@RequestParam String type, Model model, HttpServletResponse response) {
        switch (type) {
            case "TI":
                model.addAttribute("message", AlertMessages.TOKEN_INVALID.getMessage());
                return "AlertPage";
            case "TE":
                model.addAttribute("message", AlertMessages.TOKEN_EXPIRED.getMessage());
                return "AlertPage";
            case "RECOVERY_C":
                model.addAttribute("message", AlertMessages.RECOVERY_COMPLETE.getMessage());
                return "AlertPage";
            case "SW":
                model.addAttribute("message", AlertMessages.SMTH_WRONG.getMessage());
                return "AlertPage";
            case "REG_C":
                model.addAttribute("message", AlertMessages.REGISTRATION_COMPLETE.getMessage());
                return "AlertPage";
            case "RECOVERY_MM":
                model.addAttribute("message", AlertMessages.RECOVERY_MAIL_MESSSAGE.getMessage());
                return "AlertPage";
            case "NSUP":
                model.addAttribute("message", AlertMessages.NO_SUCH_USER_PRESENT.getMessage());
                return "AlertPage";
            default:
                model.addAttribute("message", AlertMessages.REGISTRATION_MAIL_MESSAGE.getMessage());
                return "AlertPage";
        }
    }
}
