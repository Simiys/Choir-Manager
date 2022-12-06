package com.simiys.choirmanager.model.tables;

import com.simiys.choirmanager.model.Role;
import com.simiys.choirmanager.model.Status;
import com.simiys.choirmanager.model.tables.Singer;
import com.simiys.choirmanager.model.tables.WorshipRefuse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
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

    @Column(name = "choir_name")
    private String choirName;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private Set<Singer> choir;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private List<WorshipRefuse> worshipRefuses;

    public ChoirDirector() {}

    public void update(List<String> worships) {
        this.monthUpdate();
        String[] directorWorships = this.worships.split(",");
        if (directorWorships.length > worships.size()) {
            for (int i = 0; i < worships.size(); i++) {
                directorWorships[i] = worships.get(i) + directorWorships[i].substring(1);
            }
        } else {
            for (int i = 0; i < directorWorships.length; i++) {
                directorWorships[i] = worships.get(i) + directorWorships[i].substring(1);
            }
        }
        this.worships = StringUtils.join(directorWorships, ",");
    }
    public void monthUpdate() {
        if (this.currentMonth != LocalDate.now().getMonthValue()) {
            this.lastMonthWorships = this.worships;
            String [] worships = "Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__".split(",");
            for (int i = LocalDate.now().lengthOfMonth(); worships.length > LocalDate.now().lengthOfMonth();i--) {
                worships = ArrayUtils.remove(worships, i - 1);
            }
            this.worships = StringUtils.join(worships, ",");
            this.currentMonth = LocalDate.now().getMonthValue();
        }
    }
    public void addSinger(Singer singer) {
        this.choir.add(singer);
    }

    public ChoirDirector(String email, String password,String firstName, String lastName) {
        this.worships ="Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__";
        this.lastMonthWorships = "Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__";
        this.currentMonth = LocalDate.now().getMonthValue();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = Role.REGENT;
        this.status = Status.ACTIVE;
        this.choir = new HashSet<>();
    }

    public void addRefuse(WorshipRefuse refuse) {
        this.worshipRefuses.add(refuse);
    }
}
