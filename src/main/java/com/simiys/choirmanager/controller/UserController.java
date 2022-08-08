package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class UserController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    SingerRepository singerRepository;

    @GetMapping("/all")
    public String getTableWithAllUsers(Model model, Principal principal) {

        ChoirDirector director = directorRepository.findByEmail(principal.getName()).orElseThrow();
        Set<Singer> singers = director.getChoir();
        director.monthUpdate();
        directorRepository.save(director);

        Singer[] s = new Singer[singers.size()];
        singers.toArray(s);
        for (Singer singer: s){
            singer.monthUpdate();
            singerRepository.save(singer);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd EE").withLocale(Locale.forLanguageTag("ru"));

        List<String> dates = new ArrayList<>(LocalDate.now().lengthOfMonth());
        for(int i = 1;i <= LocalDate.now().lengthOfMonth();i++) {
            String date = LocalDate.now().withDayOfMonth(i).format(formatter).toUpperCase(Locale.forLanguageTag("ru"));
            dates.add(date);
        }
        List<UserDTO> users = singers.stream().map(UserDTO::new).collect(Collectors.toList());
        users.add(new UserDTO(director));

        model.addAttribute("dates", dates);
        model.addAttribute("users", users);
        return "TableWithUsers";
    }

    @GetMapping("/singer")
    public String getSingerShedule(Model model, Principal principal) {
        Singer singer = singerRepository.findByEmail(principal.getName()).orElseThrow();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd EE").withLocale(Locale.forLanguageTag("ru"));

        List<String> dates = new ArrayList<>(LocalDate.now().lengthOfMonth());
        for(int i = 1;i <= LocalDate.now().lengthOfMonth();i++) {
            String date = LocalDate.now().withDayOfMonth(i).format(formatter).toUpperCase(Locale.forLanguageTag("ru"));
            dates.add(date);
        }

        UserDTO user = new UserDTO(singer);

        model.addAttribute("dates", dates);
        model.addAttribute("user", user);

        return "SingerWorships";
    }

    @GetMapping("/def")
    public String def(Principal principal) {
        System.out.println("def");
        if ((directorRepository.findByEmail(principal.getName()).isPresent())){
            return "redirect:/all";
        } else {
            return "redirect:/singer";
        }
    }



}
