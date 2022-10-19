package com.simiys.choirmanager.rest;

import com.simiys.choirmanager.dao.DirectorRepository;

import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnSingerJoinEvent;
import com.simiys.choirmanager.model.ChoirDTO;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChoirsController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    SingerRepository singerRepository;

    @GetMapping("/getAllChoirs")
    public List<ChoirDTO> getAllChoirs () {
        List<ChoirDirector> choirs1 = IteratorUtils.toList(directorRepository.findAll().iterator());
        return choirs1.stream().map(director -> new ChoirDTO(director)).collect(Collectors.toList());
    }

    @GetMapping("/joinToChoir")
    public void joinChoir(@RequestParam long id, Principal principal) {
        Singer singer = singerRepository.findByEmail(principal.getName()).orElseThrow();
        ChoirDirector director = directorRepository.findById(id).orElseThrow();
        publisher.publishEvent(new OnSingerJoinEvent(singer.getFirstName() + " " + singer.getLastName(), singer.getId(), director.getEmail(), singer.getEmail()));

    }

    @GetMapping("/joinSinger")
    public void joinSinger(@RequestParam long id, Principal principal) {
        Singer singer = singerRepository.findById(id).orElseThrow();
        ChoirDirector director = directorRepository.findByEmail(principal.getName()).orElseThrow();
        singer.setDirector(director);
        director.addSinger(singer);
        singerRepository.save(singer);
        directorRepository.save(director);
    }

}
