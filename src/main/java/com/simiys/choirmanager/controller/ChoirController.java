package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.dao.WorshipRefuseRepository;
import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.model.tables.WorshipRefuse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/choir")
public class ChoirController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    WorshipRefuseRepository refuseRepository;

    @Autowired
    DirectorRepository directorRepository;

    @GetMapping("/ref")
    public void worshipRefuse(@RequestParam int date, @RequestParam String type, Principal principal) {
        Singer singer = singerRepository.findByEmail(principal.getName()).orElseThrow();
        WorshipRefuse refuse = new WorshipRefuse(type, date, singer.getId());
        ChoirDirector director = singer.getDirector();
        refuse.setSingerName(singer.getFirstName() + " " + singer.getLastName());
        if (type.equals("В")) {
            refuse.setWorshipName("вечерни");
        } else {
            refuse.setWorshipName("утрени");
        }
        refuse.setDirector(director);
        director.addRefuse(refuse);
        refuseRepository.save(refuse);
        directorRepository.save(director);

    }


    @PostMapping("/verdict")
    public void worshipRefuseVerdict(@RequestParam long id, @RequestParam boolean ref, HttpServletResponse response) {
        WorshipRefuse refuse = refuseRepository.findById(id).orElseThrow();
        if (ref) {
            Singer singer = singerRepository.findById(refuse.getSingerId()).orElseThrow();
            int date = refuse.getDate();
            String[] worships = singer.getWorships().split(",");
            if (worships[date - 1].equals("П")) {
                if (refuse.getType().equals("У")) {
                    worships[date - 1] = "В";
                } else {
                    worships[date - 1] = "У";
                }
            } else {
                worships[date - 1] = "Н";
            }
            singer.setWorships(StringUtils.join(worships, ","));
            singerRepository.save(singer);
        }
        refuseRepository.delete(refuse);
        response.setStatus(204);
    }
}
