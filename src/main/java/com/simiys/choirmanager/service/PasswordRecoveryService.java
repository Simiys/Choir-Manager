package com.simiys.choirmanager.service;

import com.simiys.choirmanager.dao.DirectorPassRecRepository;
import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerPassRecRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.model.tables.tokens.DirectorPasswordRecoveryToken;
import com.simiys.choirmanager.model.tables.tokens.SingerPasswordRecoveryToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryService {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private SingerPassRecRepository singerPassRecRepository;

    @Autowired
    private DirectorPassRecRepository directorPassRecRepository;

    public void createSingerPassRecovToken(Singer singer, String token) {
        SingerPasswordRecoveryToken recoveryToken = new SingerPasswordRecoveryToken(singer, token);
        singerPassRecRepository.save(recoveryToken);
    }

    public SingerPasswordRecoveryToken findSingerToken(String token) {
        return singerPassRecRepository.findByToken(token).orElseThrow();
    }

    public void createDirectorPassRecovToken(ChoirDirector director, String token) {
        DirectorPasswordRecoveryToken recoveryToken = new DirectorPasswordRecoveryToken(director, token);
        directorPassRecRepository.save(recoveryToken);
    }

    public DirectorPasswordRecoveryToken findDirectorToken(String token) {
        return directorPassRecRepository.findByToken(token).orElseThrow();
    }

}
