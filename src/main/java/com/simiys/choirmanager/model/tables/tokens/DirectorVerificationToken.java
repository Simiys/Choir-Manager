package com.simiys.choirmanager.model.tables.tokens;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "directorVerToken")
@Data
@NoArgsConstructor
public class DirectorVerificationToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @Column
    private Date expireDate;


    @JoinColumn(name = "dir_id")
    @OneToOne(targetEntity = ChoirDirector.class, cascade = CascadeType.ALL)
    private ChoirDirector director;

    public DirectorVerificationToken(ChoirDirector director, String token) {
        this.token = token;
        this.director = director;
        this.expireDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, 1440);
        return new Date(calendar.getTime().getTime());
    }
}
