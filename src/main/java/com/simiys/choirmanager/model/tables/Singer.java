package com.simiys.choirmanager.model.tables;

import com.simiys.choirmanager.model.Role;
import com.simiys.choirmanager.model.Status;
import com.simiys.choirmanager.model.VoiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
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

    @Column(name = "voice_type")
    @Enumerated(EnumType.STRING)
    private VoiceType voiceType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    @Nullable
    private ChoirDirector director;

    public Singer() {}

    public void setWorships(String[] worships) {
        this.worships = StringUtils.join(worships, ",");
    }

    public void update(List<String> worships) {
        this.monthUpdate();
        String[] singerWorships = this.worships.split(",");
        if (singerWorships.length > worships.size()) {
            for (int i = 0; i < worships.size(); i++) {
                singerWorships[i] = worships.get(i) + singerWorships[i].substring(1);
            }
        } else {
            for (int i = 0; i < singerWorships.length; i++) {
                singerWorships[i] = worships.get(i) + singerWorships[i].substring(1);
            }
        }
        this.worships = StringUtils.join(singerWorships, ",");
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
    public String[] getArrayWorships() {
        return this.worships.split(",");
    }
    public Singer(String email, String password,String firstName, String lastName) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        this.worships = "Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__";
        this.lastMonthWorships = "Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__,Н__";
        this.currentMonth = LocalDate.now().getMonthValue();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = encoder.encode(password);
        this.director = null;
        this.role = Role.SINGER;
        this.status = Status.ACTIVE;
    }
}