//package com.simiys.choirmanager.security;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if (name == null || password == null){
//            throw new AuthenticationException ("Data is null") {};
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
