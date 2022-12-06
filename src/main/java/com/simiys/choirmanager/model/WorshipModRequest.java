package com.simiys.choirmanager.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorshipModRequest {

    private int date;
    private int modType;
    private int worshipType;
}
