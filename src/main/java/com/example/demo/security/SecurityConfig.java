package com.example.demo.security;

/*
import com.example.demo.filter.CustomAuthenticationFilter;
import com.example.demo.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
*/

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {}//extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//
//        /*Path aperto*/
//        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/account/**").permitAll();
//        http.authorizeRequests().antMatchers(POST, "/api/account/**").permitAll();
//
//        /*Path a cui possono accedere gli studenti*/
//        http.authorizeRequests().antMatchers(GET, "/api/student/**").hasAnyAuthority("ROLE_STUDENT");
//        http.authorizeRequests().antMatchers(POST, "/api/student/**").hasAnyAuthority("ROLE_STUDENT");
//
//        /*Path a cui possono accedere i docenti*/
//        http.authorizeRequests().antMatchers(GET, "/api/teacher/**").hasAnyAuthority("ROLE_TEACHER");
//        http.authorizeRequests().antMatchers(POST, "/api/teacher/**").hasAnyAuthority("ROLE_TEACHER");
//
//        /*Path a cui possono accedere gli amministratori*/
//        http.authorizeRequests().antMatchers(GET, "/api/admin/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/admin/**").hasAnyAuthority("ROLE_ADMIN");
//
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

