package com.simiys.choirmanager.security;

import com.simiys.choirmanager.dao.DirectorRepository;
import com.simiys.choirmanager.dao.SingerRepository;
import com.simiys.choirmanager.model.ChoirDirector;
import com.simiys.choirmanager.model.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SingerRepository singerRepository;

    private final DirectorRepository directorRepository;


    @Autowired
    public UserDetailsServiceImpl(SingerRepository singerRepository, DirectorRepository directorRepository) {
        this.singerRepository = singerRepository;
        this.directorRepository = directorRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(singerRepository.findByEmail(username).isEmpty()) {
            ChoirDirector director = directorRepository.findByEmail(username).orElseThrow(
                    () -> new UsernameNotFoundException("user not found"));
            return SecurityUser.fromDierctor(director);
        } else {
            Singer singer = singerRepository.findByEmail(username).orElseThrow(
                    () -> new UsernameNotFoundException("user not found"));
            return SecurityUser.fromSinger(singer);
        }
    }
}
