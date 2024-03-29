package com.simiys.choirmanager.model.tables.tokens;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class DirectorPasswordRecoveryToken {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String token;

    @Column
    private Date expireDate;

    @OneToOne(targetEntity = ChoirDirector.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dir_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ChoirDirector director;

    private Date calculateExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, 15);
        return new Date(calendar.getTime().getTime());
    }

    public DirectorPasswordRecoveryToken(ChoirDirector director, String token) {
        this.token = token;
        this.director = director;
        this.expireDate = calculateExpireDate();
    }

}
