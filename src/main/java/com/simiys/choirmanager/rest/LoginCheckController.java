package com.simiys.choirmanager.rest;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.DTOForLoginCheck;
import com.simiys.choirmanager.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginCheckController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorRepository directorRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/logincheck")
    public String checkLoginData (@RequestBody DTOForLoginCheck dto) {
        if (singerRepository.findByEmail(dto.getUsername()).isEmpty() && directorRepository.findByEmail(dto.getUsername()).isEmpty()) {
            return "unknown_user";
        }
        if (singerRepository.findByEmail(dto.getUsername()).isPresent()) {
            String enc_password1 = singerRepository.findByEmail(dto.getUsername()).get().getPassword();
            if (singerRepository.findByEmail(dto.getUsername()).get().getStatus().equals(Status.REGISTRATION)) {
                return "on_reg";
            }
            if (singerRepository.findByEmail(dto.getUsername()).get().getStatus().equals(Status.BANNED)) {
                return "banned";
            }
            if (!encoder.matches(dto.getPassword(), enc_password1)) {
                return "incorrect_pass";
            }
        }
        if (directorRepository.findByEmail(dto.getUsername()).isPresent()) {
            String enc_password2 = directorRepository.findByEmail(dto.getUsername()).get().getPassword();
            if (directorRepository.findByEmail(dto.getUsername()).get().getStatus().equals(Status.REGISTRATION)) {
                return "on_reg";
            }
            if (directorRepository.findByEmail(dto.getUsername()).get().getStatus().equals(Status.BANNED)) {
                return "banned";
            }
            if (!encoder.matches(dto.getPassword(), enc_password2)) {
                return "incorrect_pass";
            }
        }
        return "ok";
    }
}
