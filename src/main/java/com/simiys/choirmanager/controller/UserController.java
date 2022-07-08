package com.simiys.choirmanager.controller;

import com.simiys.choirmanager.dao.UserRepository;
import com.simiys.choirmanager.model.User;
import com.simiys.choirmanager.model.UserDTO;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    public String getTableWithAllUsers(Model model) {

        repository.findAll().forEach(user -> {
            user.monthUpdate();
            repository.save(user);
        });

       // System.out.println(principal.getName() + " -username");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd EE").withLocale(Locale.forLanguageTag("ru"));

        List<String> dates = new ArrayList<>(LocalDate.now().lengthOfMonth());
        for(int i = 1;i <= LocalDate.now().lengthOfMonth();i++) {
            String date = LocalDate.now().withDayOfMonth(i).format(formatter).toUpperCase(Locale.forLanguageTag("ru"));
            dates.add(date);
        }

        List<User> userList = IteratorUtils.toList(repository.findAll().iterator());
        List<UserDTO> userlist = userList.stream().map(UserDTO::new).collect(Collectors.toList());
        //userlist.forEach(user -> user.worships = user.worships.subList(0,LocalDate.now().lengthOfMonth()));

        model.addAttribute("dates", dates);
        model.addAttribute("users", userlist);
        return "TableWithUsers";
    }
}
