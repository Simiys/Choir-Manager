package com.simiys.choirmanager.security;

import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import com.simiys.choirmanager.model.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final boolean isActive;
    private final List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromSinger(Singer singer) {
        return new org.springframework.security.core.userdetails.User(
                singer.getEmail(), singer.getPassword(),
                singer.getStatus().equals(Status.ACTIVE),
                singer.getStatus().equals(Status.ACTIVE),
                singer.getStatus().equals(Status.ACTIVE),
                singer.getStatus().equals(Status.ACTIVE),
                singer.getRole().getAuthorities()

        );
    }

    public static UserDetails fromDierctor(ChoirDirector director) {
        return new org.springframework.security.core.userdetails.User(
                director.getEmail(), director.getPassword(),
                director.getStatus().equals(Status.ACTIVE),
                director.getStatus().equals(Status.ACTIVE),
                director.getStatus().equals(Status.ACTIVE),
                director.getStatus().equals(Status.ACTIVE),
                director.getRole().getAuthorities()

        );
    }
}
