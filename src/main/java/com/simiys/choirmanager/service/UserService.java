package com.simiys.choirmanager.service;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.DirectorTokenRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.dao.SingerTokenRepository;
import com.simiys.choirmanager.model.*;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.model.tables.tokens.DirectorVerificationToken;
import com.simiys.choirmanager.model.tables.tokens.SingerVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

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
        director.setChoirName(user.getChoirName());
        director.setPassword(encoder.encode(user.getPassword()));
        directorRepository.save(director);
        setDirectorForeingKey(director);
        director.monthUpdate();
        return director;
    }

    public Singer registrSinger(UserForRegistration user) {
        Singer singer = new Singer(
                user.getEmail(), encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
        singer.setStatus(Status.REGISTRATION);
        singer.setPassword(encoder.encode(user.getPassword()));
        singer.monthUpdate();
        singer.setVoiceType(voiceType(user.getVoiceType()));
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

    public VoiceType voiceType(String type) {
        return Arrays.stream(VoiceType.values()).filter(voiceType -> voiceType.getType().equals(type)).findFirst().get();
    }

    public void autoManage(List<Singer> singers, int date, int type) {
        Map<Singer, Integer> rating = new HashMap<>();
        Integer points = 0;
        for (Singer singer: singers) {
            if (singer.getArrayWorships()[date - 1].charAt(1) == 'c') {
                continue;
            }
            if (singer.getArrayWorships()[date - 1].charAt(1) == 'd') {
                points = -10;
            }
            if (singer.getArrayWorships()[date - 1].charAt(1) == 'w') {
                points = 10;
            }
            for (int i = date - 2; i >= 0; i--) {
                if (singer.getArrayWorships()[i].charAt(1) == '_') {
                    points++;
                } else {
                    break;
                }
            }
            rating.put(singer, points);
        }
        if (rating.entrySet().size() == 0) {
            return;
        }

        Singer singer = rating.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        String[] arr = singer.getArrayWorships();
        if (type == 1) {
            if (arr[date - 1].charAt(0) != 'Н' && arr[date - 1].charAt(0) != 'У') {
                arr[date - 1] = "П" + singer.getArrayWorships()[date - 1].substring(1);
            } else {
                arr[date - 1] = "У" + singer.getArrayWorships()[date - 1].substring(1);
            }
        } else {
            if (arr[date - 1].charAt(0) != 'Н' && arr[date - 1].charAt(0) != 'В') {
                arr[date - 1] = "П" + singer.getArrayWorships()[date - 1].substring(1);
            } else {
                arr[date - 1] = "В" + singer.getArrayWorships()[date - 1].substring(1);
            }
        }
        singer.setWorships(arr);
        singerRepository.save(singer);
    }
}
