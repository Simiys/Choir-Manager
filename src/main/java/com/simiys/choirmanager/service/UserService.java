package com.simiys.choirmanager.service;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.DirectorTokenRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.dao.SingerTokenRepository;
import com.simiys.choirmanager.model.*;
import com.simiys.choirmanager.model.tokens.DirectorVerificationToken;
import com.simiys.choirmanager.model.tokens.SingerVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Service
public class UserService {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    SingerTokenRepository singerTokenRepository;

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorTokenRepository directorTokenRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ChoirDirector registrRegent(UserForRegistration user){
        ChoirDirector director = new ChoirDirector(
                user.getEmail(), encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
        director.setStatus(Status.REGISTRATION);
        director.setPassword(encoder.encode(user.getPassword()));
        directorRepository.save(director);
        setDirectorForeingKey(director);
        return director;
    }

    public Singer registrSinger(UserForRegistration user) {
        Singer singer = new Singer(
                user.getEmail(), encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
        singer.setStatus(Status.REGISTRATION);
        singer.setPassword(encoder.encode(user.getPassword()));
        singerRepository.save(singer);
        return singer;
    }

    public void createSingerToken(String token, Singer singer) {
        SingerVerificationToken vToken = new SingerVerificationToken(singer, token);
        singerTokenRepository.save(vToken);
    }

    public void createDirectorToken(String token, ChoirDirector director) {
        DirectorVerificationToken verificationToken = new DirectorVerificationToken(director, token);
        directorTokenRepository.save(verificationToken);
    }

    public SingerVerificationToken getSingerVerificationToken(String token) {
        return singerTokenRepository.findByToken(token).orElseThrow();
    }

    public DirectorVerificationToken getDirectorVerificationToken(String token) {
        return directorTokenRepository.findByToken(token).orElseThrow();
    }
    private void setDirectorForeingKey(ChoirDirector director) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simiys_db", "Simiys", "1234");
            Statement stmt = conn.createStatement();
            String query = "UPDATE `simiys_db`.`directors` SET `director_id` = '"+ director.getId() + "' WHERE (`id` = '"+ director.getId() + "');";
            stmt.execute(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("UserService setDirectorForeingKey is on exeption");
        }
    }

}
