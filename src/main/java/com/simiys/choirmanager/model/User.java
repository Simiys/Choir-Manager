package com.simiys.choirmanager.model;

import com.simiys.choirmanager.dao.UserRepository;

import javax.persistence.*;
import java.util.*;
import java.time.LocalDate;


@Entity
@Table(name = "usrs")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;

    @Column
    private String worships;

    @Column
    private String lastMonthWorships;

    @Column
    private int currentMonth;

    public int getCurrentMonth() {
        return currentMonth;
    }

    public String getLastMonthWorships() {
        return lastMonthWorships;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getWorships() {
        List<String> list =  Arrays.asList(this.worships.split(","));
        return list;
    }
    public User() {}

    public User(UserForTemplate userForTemplate) {
        this.username = userForTemplate.getUsername();
        this.worships = userForTemplate.getNumbersString();
    }

    public User (String username) {
        this.username = username;
        this.worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.lastMonthWorships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.currentMonth = LocalDate.now().getMonthValue();
    }

    public void update(String worships) {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            this.worships = worships;
            this.currentMonth = LocalDate.now().getMonthValue();
        } else {
            this.worships = worships;
        }
    }

    public void monthUpdate() {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            this.worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
            this.currentMonth = LocalDate.now().getMonthValue();

        }
    }
}