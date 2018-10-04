package com.apexmob.casus.sample.websocket.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String REALM="MY_TEST_REALM";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().realmName(REALM);//.authenticationEntryPoint(getBasicAuthEntryPoint());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager retVal = new InMemoryUserDetailsManager();
        retVal.createUser(buildUserDetails("John"));
        retVal.createUser(buildUserDetails("Jane"));
        retVal.createUser(buildUserDetails("Mike"));
        retVal.createUser(buildUserDetails("Molly"));

        return retVal;
    }

    private UserDetails buildUserDetails(String username) {
        return User.withDefaultPasswordEncoder()
                        .username(username)
                        .password("password")
                        .roles("USER")
                        .build();
    }
}
