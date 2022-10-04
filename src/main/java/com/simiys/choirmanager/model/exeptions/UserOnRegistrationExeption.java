package com.simiys.choirmanager.model.exeptions;

import org.springframework.security.core.AuthenticationException;

public class UserOnRegistrationExeption extends AuthenticationException {
    public UserOnRegistrationExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserOnRegistrationExeption(String msg) {
        super(msg);
    }
}
