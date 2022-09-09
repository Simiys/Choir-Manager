package com.simiys.choirmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryContainer {

    private String password;
    private String repeatPass2word;

}
