package com.codebattles.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private DataSource dataSource;
  
  @Value("${spring.queries.users-query}")
  private String usersQuery;
  
  @Value("${spring.queries.roles-query}")
  private String rolesQuery;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth)
          throws Exception {
      auth.
          jdbcAuthentication()
              .usersByUsernameQuery(usersQuery)
              .authoritiesByUsernameQuery(rolesQuery)
              .dataSource(dataSource)
              .passwordEncoder(bCryptPasswordEncoder);
  }
  
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
          .cors().disable()
          .csrf()
          .ignoringAntMatchers("/dashboard/**")
          .and()
          .authorizeRequests()
              .antMatchers("/", "/ranking", "/register", "/libs/**").permitAll()
              .antMatchers("/dashboard").hasAuthority("ADMIN")
              .anyRequest().authenticated()
              .and()
          .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/")
              .usernameParameter("email")
              .passwordParameter("password")
              .permitAll()
              .and()
          .logout()
              .permitAll();
  }

}
