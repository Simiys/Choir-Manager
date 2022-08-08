package com.simiys.choirmanager.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private List<String> worships;

    public UserDTO(Singer singer) {
        this.firstName = singer.getFirstName();
        this.lastName = singer.getLastName();
        this.worships = new ArrayList<>(Arrays.asList(singer.getWorships().split(",")));
        this.username = singer.getEmail();
    }

    public UserDTO(ChoirDirector director) {
        this.firstName = director.getFirstName();
        this.lastName = director.getLastName();
        this.worships = new ArrayList<>(Arrays.asList(director.getWorships().split(",")));
        this.username = director.getEmail();
    }
}
