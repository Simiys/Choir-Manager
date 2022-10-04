package com.simiys.choirmanager.model.exeptions;

import org.springframework.security.core.AuthenticationException;

public class BannedStatuExeption extends AuthenticationException {

    public BannedStatuExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BannedStatuExeption(String msg) {
        super(msg);
    }
}
