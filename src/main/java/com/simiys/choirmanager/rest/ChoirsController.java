package com.simiys.choirmanager.rest;

import com.simiys.choirmanager.dao.DirectorRepository;

import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.events.OnSingerJoinEvent;
import com.simiys.choirmanager.model.ChoirDTO;
import com.simiys.choirmanager.model.VoiceType;
import com.simiys.choirmanager.model.WorshipModRequest;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.service.UserService;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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

    @Autowired
    UserService service;

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

    @PostMapping("/updateWorships")
    public void updateAllWorships(@RequestBody List<List<String>> list) {
        List<String> forDirector = list.get(list.size() - 1);
        ChoirDirector director = directorRepository.findByEmail(forDirector.get(0)).orElseThrow();
        director.update(forDirector.subList(1, forDirector.size()));
        directorRepository.save(director);
        if(list.size() > 1) {
            list.remove(list.size() - 1);
            list.forEach(l -> {
                Singer singer = singerRepository.findByEmail(l.get(0)).get();
                singer.update(l.subList(1, l.size()));
                singerRepository.save(singer);
            });
        }
    }

    @PostMapping("/worshipModifier")
    public void addModifier(@RequestParam String modType, @RequestParam int day, @RequestParam String worshipType, Principal principal) {
        System.out.println("CC 82 started");
        Singer singer = singerRepository.findByEmail(principal.getName()).get();
        String[] worships = singer.getWorships().split(",");
        String worship = worships[day - 1];
        if (worshipType.equals("v")) {
            worships[day - 1] = worship.substring(0,2) + modType;
        } else {
            worships[day - 1] = worship.charAt(0) + modType + worship.substring(2);
        }
        singer.setWorships(worships);
        singerRepository.save(singer);
    }

    @PostMapping("/autoManage")
    public String autoManage(Principal principal, @RequestParam int date, @RequestParam int type) {
        if (date <= 0 || date > LocalDate.now().lengthOfMonth()) {
            return "error";
        }
        ChoirDirector director = directorRepository.findByEmail(principal.getName()).get();
        Set<Singer> singers = director.getChoir();

        List<Singer> voiceS = singers.stream().filter(singer -> singer.getVoiceType().equals(VoiceType.S)).collect(Collectors.toList());
        List<Singer> voiceA = singers.stream().filter(singer -> singer.getVoiceType().equals(VoiceType.A)).collect(Collectors.toList());
        List<Singer> voiceT = singers.stream().filter(singer -> singer.getVoiceType().equals(VoiceType.T)).collect(Collectors.toList());
        List<Singer> voiceB = singers.stream().filter(singer -> singer.getVoiceType().equals(VoiceType.B)).collect(Collectors.toList());


        service.autoManage(voiceS, date, type);
        service.autoManage(voiceA, date, type);
        service.autoManage(voiceB, date, type);
        service.autoManage(voiceT, date, type);

        return "ok";
    }

    @PostMapping("/worshipMod")
    public String setWorshipMod(Principal principal, @RequestBody WorshipModRequest request) {
        if (request.getDate() < 1 || request.getDate() > LocalDate.now().lengthOfMonth()) {
            return "dateErr";
        }
        Singer singer = singerRepository.findByEmail(principal.getName()).get();
        String[] arr = singer.getWorships().split(",");
        String worship = arr[request.getDate() - 1];
        String mod = "";
        if (request.getModType() == 1) {
            mod = "_";
        }
        if (request.getModType() == 2) {
            mod = "w";
        }
        if (request.getModType() == 3) {
            mod = "d";
        }
        if (request.getModType() == 4) {
            mod = "c";
        }
        if (request.getWorshipType() == 1) {
            arr[request.getDate() - 1] = worship.charAt(0) + mod + worship.charAt(2);
            singer.setWorships(arr);
            singerRepository.save(singer);
            return "ok";
        }
        arr[request.getDate() - 1] = worship.substring(0,2) + mod;
        singer.setWorships(arr);
        singerRepository.save(singer);
        return "ok";
    }
}
