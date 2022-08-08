package com.simiys.choirmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "directors")
@AllArgsConstructor
public class ChoirDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private long id;

    @Column(unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "worships")
    private String worships;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "last_month_worships")
    private String lastMonthWorships;

    @Column(name = "current_month")
    private int currentMonth;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private Set<Singer> choir;

    public ChoirDirector() {}

    public void update(List<String> worships) {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.worships = StringUtils.join(worships,",");
            this.currentMonth = LocalDate.now().getMonthValue();
        } else {
            this.worships = StringUtils.join(worships,",");
        }
    }
    public void monthUpdate() {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            this.worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
            this.currentMonth = LocalDate.now().getMonthValue();
        }
    }
    public void addSinger(Singer singer) {
        this.choir.add(singer);
    }

    public ChoirDirector(String email, String password,String firstName, String lastName) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        this.worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.lastMonthWorships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.currentMonth = LocalDate.now().getMonthValue();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = encoder.encode(password);
        this.role = Role.REGENT;
        this.status = Status.ACTIVE;
        this.choir = new HashSet<>();
    }
}
