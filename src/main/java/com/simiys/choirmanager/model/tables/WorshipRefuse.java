package com.simiys.choirmanager.model.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorshipRefuse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String type;

    @Column
    private int date;

    @Column
    private long singerId;

    @Column
    private String singerName;

    @Column
    private String worshipName;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private ChoirDirector director;

    public WorshipRefuse (String type, int date, long singerId) {
        this.type = type;
        this.date = date;
        this.singerId = singerId;
    }

}
