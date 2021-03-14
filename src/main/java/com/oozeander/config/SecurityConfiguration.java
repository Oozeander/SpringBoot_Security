package com.oozeander.config;

import com.oozeander.model.security.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("Oozeander").password(passwordEncoder().encode("Ashura"))
                .roles(Role.ADMIN.name())
                .and()
            .withUser("bketrouci").password(passwordEncoder().encode("1234"))
                .roles(Role.STUDENT.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // for non-human interaction, enabled if used by clients
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/css/*", "/js/*").permitAll()
                //.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.STUDENT.name()) # @Secured({"ROLE_STUDENT"})
                //.antMatchers("/management/api/**").hasAnyRole(Role.ADMIN.name(), Role.ADMIN_TRAINEE.name())
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}