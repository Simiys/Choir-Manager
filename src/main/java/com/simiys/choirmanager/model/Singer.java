package com.simiys.choirmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(exclude = "director")
@Table(name = "singers")
@AllArgsConstructor
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private ChoirDirector director;

    public Singer() {}

    public void update(List<String> worships) {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            this.worships = StringUtils.join(worships,",");
            this.currentMonth = LocalDate.now().getMonthValue();
        } else {
            this.worships = StringUtils.join(worships,",");
        }
    }
    public void monthUpdate() {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            String [] worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н".split(",");
            for (int i = LocalDate.now().lengthOfMonth(); worships.length > LocalDate.now().lengthOfMonth();i--) {
                worships = ArrayUtils.remove(worships, i);
            }
            this.worships = StringUtils.join(worships, ",");
            this.currentMonth = LocalDate.now().getMonthValue();
        }
    }
    public Singer(String email, String password,String firstName, String lastName) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        this.worships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.lastMonthWorships = "Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н,Н";
        this.currentMonth = LocalDate.now().getMonthValue();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = encoder.encode(password);
        this.director = new ChoirDirector();
        this.role = Role.SINGER;
        this.status = Status.ACTIVE;
    }
}