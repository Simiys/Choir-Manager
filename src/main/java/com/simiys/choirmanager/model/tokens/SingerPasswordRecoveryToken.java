package com.simiys.choirmanager.model.tokens;

import com.simiys.choirmanager.model.Singer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "pass_rec_singer")
@Data
@NoArgsConstructor
public class SingerPasswordRecoveryToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String token;

    @Column
    private Date expireDate;

    @OneToOne(targetEntity = Singer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    private Date calculateExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, 15);
        return new Date(calendar.getTime().getTime());
    }

    public SingerPasswordRecoveryToken(Singer singer, String token) {
        this.token = token;
        this.singer = singer;
        this.expireDate = calculateExpireDate();
    }
}
