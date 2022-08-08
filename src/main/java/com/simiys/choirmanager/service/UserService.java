package com.simiys.choirmanager.service;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.Status;
import com.simiys.choirmanager.model.UserForRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    SingerRepository singerRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registrRegent(UserForRegistration user){
        ChoirDirector director = new ChoirDirector(
                user.getEmail(),encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
        director.setStatus(Status.BANNED);
        directorRepository.save(director);
    }

    public void registrSinger(UserForRegistration user) {
        Singer singer = new Singer(
                user.getEmail(), encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
        singer.setStatus(Status.BANNED);
        singerRepository.save(singer);
    }
}
