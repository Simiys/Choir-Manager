package com.simiys.choirmanager.model;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoirDTO {

    private String choirName;
    private String directorSurname;
    private String directorName;
    private long id;

    public ChoirDTO (ChoirDirector director) {
        this.choirName = director.getChoirName();
        this.directorName = director.getFirstName();
        this.directorSurname = director.getLastName();
        this.id = director.getId();
    }
}
