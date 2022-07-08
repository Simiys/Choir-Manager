package com.simiys.choirmanager.rest;


import com.simiys.choirmanager.dao.UserRepository;
import com.simiys.choirmanager.model.User;
import com.simiys.choirmanager.model.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.collections.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserRepository repository;

    @GetMapping("/{name}")
    public User getUserByName (@RequestParam String name) {
        repository.findAll().forEach(user -> {
            user.monthUpdate();
            repository.save(user);
        });
        return repository.getByUsername(name).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        });
    }

    @GetMapping("/all")
    public List<User> getAllUsers () {
        repository.findAll().forEach(user -> {
            user.monthUpdate();
            repository.save(user);
        });
        return IteratorUtils.toList(repository.findAll().iterator());
    }

    @PostMapping("/new")
    public User newUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getUsername());
        repository.save(user);
        return user;
    }

    @PostMapping("/updateWorships")
    public void updateAllWorships (@RequestBody List<List<String>> list) {
        System.out.println("update started");
        list.forEach(l -> {
            User user = repository.getByUsername(l.get(0)).get();
            user.update(StringUtils.join(l.subList(1,l.size()),','));
            System.out.println(StringUtils.join(l.subList(1,l.size()),',') + " to update");
            repository.save(user);
            System.out.println("user " + user.getUsername() + " updated");
            System.out.println(user.getWorships());
        });
        System.out.println("all right");

    }

}
