package com.simiys.choirmanager.model;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import com.simiys.choirmanager.model.tables.Singer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private List<String> worships;
    private String voice;

    public UserDTO(Singer singer) {
        this.firstName = singer.getFirstName();
        this.lastName = singer.getLastName();
        this.worships = new ArrayList<>(Arrays.asList(singer.getWorships().split(",")));
        this.username = singer.getEmail();
        this.voice = singer.getVoiceType().getType();
    }

    public UserDTO(ChoirDirector director) {
        this.firstName = director.getFirstName();
        this.lastName = director.getLastName();
        this.worships = new ArrayList<>(Arrays.asList(director.getWorships().split(",")));
        this.username = director.getEmail();
        this.voice = "";
    }
}
