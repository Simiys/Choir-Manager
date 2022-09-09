package com.simiys.choirmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class UserForRegistration {

    private String email;
    private String password;
    private String matchingPassword;
    private String firstName;
    private String lastName;
    private boolean isRegent;

    public boolean validPassword() {
        return this.password.equals(this.matchingPassword);
    }

    public boolean validEmail() {
        return Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(this.email).matches();
    }

    public boolean allFieldsFilled() throws IllegalAccessException {
        for(Field f : getClass().getDeclaredFields()) {
            if(f.get(this).equals(null)) {
                return false;
            }
            if(f.getClass().equals(String.class)){
                if (f.get(this).equals("")){
                    return false;
                }
            }
        }
        return true;
    }

}
