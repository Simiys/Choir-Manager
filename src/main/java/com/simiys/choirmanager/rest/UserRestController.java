package com.simiys.choirmanager.rest;


import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Role;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    SingerRepository singerRepository;

    @Autowired
    DirectorRepository directorRepository;

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
}
