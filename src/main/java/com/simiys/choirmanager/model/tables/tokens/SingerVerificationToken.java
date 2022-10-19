package com.simiys.choirmanager.model.tables.tokens;

import com.simiys.choirmanager.model.tables.Singer;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SingerVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    private Date expireDate;

    @JoinColumn(name = "singer_id")
    @OneToOne(targetEntity = Singer.class, cascade = CascadeType.ALL)
    private Singer singer;

    public SingerVerificationToken(Singer singer, String token) {
        this.token = token;
        this.singer = singer;
        this.expireDate = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, 1440);
        return new Date(calendar.getTime().getTime());
    }
}
