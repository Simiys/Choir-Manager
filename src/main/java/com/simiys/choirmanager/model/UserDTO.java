package com.simiys.choirmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

//this class needed for interacting with freemaker template, cause idk how to store list in one field of entity of h2 DB
@Data
@NoArgsConstructor
public class UserDTO {

    public String username;

    public List<String> worships;

    private String password;

    public String getWorshipsString() {
        return StringUtils.join(worships,',');
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.worships = user.getWorships();
        System.out.println("(UserDTO constructor) from User: " + user.getUsername() + " " + user.getWorships());
    }

    public UserDTO (String username, String password) {
        this.password = password;
        this.username = username;
    }


}
