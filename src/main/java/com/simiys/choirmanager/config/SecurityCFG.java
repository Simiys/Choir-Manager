package com.simiys.choirmanager.config;

import com.simiys.choirmanager.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityCFG extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityCFG(@Qualifier("userDetailsServiceImplementation") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/passrec/**").anonymous()
                .antMatchers("/logincheck").anonymous()
                .antMatchers("/confirmRegistrationForSinger").anonymous()
                .antMatchers("/confirmRegistrationForDirector").anonymous()
                .antMatchers("/choirlist").hasAuthority(Permission.JOIN_CHOIRS.getPermission())
                .antMatchers("/api/joinToChoir").hasAuthority(Permission.JOIN_CHOIRS.getPermission())
                .antMatchers("/choirs/ref").hasAuthority(Permission.JOIN_CHOIRS.getPermission())
                .antMatchers("/choirs/verdict").hasAuthority(Permission.MANAGE_CHOIR.getPermission())
                .antMatchers("/refuseMessages").hasAuthority(Permission.MANAGE_CHOIR.getPermission())
                .antMatchers("/sendRegEmail").anonymous()
                .antMatchers("/").anonymous()
                .antMatchers("/api/registr").anonymous()
                .antMatchers("/alert").anonymous()
                .antMatchers("/registration").anonymous()
                .antMatchers("/alert").anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/def", true).permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .deleteCookies("RECOVERYTOKEN")
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
