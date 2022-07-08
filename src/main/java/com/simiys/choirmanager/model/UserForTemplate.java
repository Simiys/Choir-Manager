package com.simiys.choirmanager.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

//this class needed for interacting with freemaker template, cause idk how to store list in one field of entity of h2 DB
public class UserForTemplate {

    public String username;

    public List<String> worships;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getWorships() { return worships;
    }

    public String getNumbersString() {
        return StringUtils.join(worships,',');
    }

    public void setWorships(List<String> worships) {
        this.worships = worships;
    }

    public UserForTemplate (User user) {
        this.username = user.getUsername();
        this.worships = user.getWorships();
        System.out.println("debug: " + user.getWorships());
    }

    public UserForTemplate(String username, String[] worships) {
        this.username = username;
        this.worships = Arrays.asList(worships);
    }


}
