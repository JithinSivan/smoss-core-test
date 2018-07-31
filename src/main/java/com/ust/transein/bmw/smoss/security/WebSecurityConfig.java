package com.ust.transein.bmw.smoss.security;


//import com.ust.transein.model.LoginDetails;
//import com.ust.transein.service.AppUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "*", maxAge = 3600)


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private AppUserDetailService appUserDetailService;*/

@Autowired
  private DataSource dataSource;

  @Override
    protected void configure(HttpSecurity http) throws Exception {

/*      http.headers().cacheControl();
      http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .anyRequest().authenticated()
                .and()

//                .permitAll();

                 //We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/user/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);*/

      /*http.headers().cacheControl();
      http.authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers(HttpMethod.POST, "/user/login").permitAll()
              .anyRequest().authenticated();
      http.addFilterBefore(new JWTAuthenticationFilter(),
              UsernamePasswordAuthenticationFilter.class)
              .addFilterBefore(new JWTAuthenticationFilter(),
              UsernamePasswordAuthenticationFilter.class);*/

     /* http.csrf().ignoringAntMatchers("/user/login");
      http.authorizeRequests()
              .antMatchers("/user/login")
              .permitAll()*/
              //.antMatchers("/**/*")

      /*
       http.authorizeRequests()
  .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
  .anyRequest().permitAll()
  .and()
    .formLogin().loginPage("/login")
    .usernameParameter("username").passwordParameter("password")
  .and()
    .logout().logoutSuccessUrl("/login?logout")
   .and()
   .exceptionHandling().accessDeniedPage("/403")
  .and()
    .csrf();

       */

      http.csrf().disable().authorizeRequests()
            //  .antMatchers("/admin").hasRole("ADMIN")
              //.antMatchers("/user/login").hasRole("ADMIN");
              .antMatchers(HttpMethod.POST, "/user/login").access("hasRole('ROLE_USER_01')")
              .anyRequest().authenticated().and()
              .logout();

      http
              .addFilterBefore(new JWTLoginFilter("/user/login", authenticationManager()),
                      UsernamePasswordAuthenticationFilter.class)
              // And filter other requests to check the presence of JWT in header
              .addFilterBefore(new JWTAuthenticationFilter(),
                      UsernamePasswordAuthenticationFilter.class);

/*

              http.csrf().disable().authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers(HttpMethod.POST, "/user/login").permitAll()
              .anyRequest().authenticated();
      http
              .addFilterBefore(new JWTLoginFilter("/user/login", authenticationManager()),
              UsernamePasswordAuthenticationFilter.class)
              // And filter other requests to check the presence of JWT in header
              .addFilterBefore(new JWTAuthenticationFilter(),
                      UsernamePasswordAuthenticationFilter.class);*/
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  final String findUserQuery = "select username, password,true "
                                        + "from aggregator.jkstest "
                                            + "where username = ?"; */

        final String findUserQuery = "select username,password,true from sm.customer_credentials where username= ? ";

        /*final String findRoles = "select authority_name "
                                        + "from sm.customer_authority "
                                            + "where user_id = (select id from sm.customer_credentials where username= ?)"; */

        final String findRoles = "select user_id, authority_name "
                                 + "from sm.customer_authority "
                                    + "where user_id = (select id from sm.customer_credentials where username= ?)";
                auth.jdbcAuthentication().dataSource(dataSource)
                        .usersByUsernameQuery(findUserQuery)
                        .authoritiesByUsernameQuery(findRoles)
                        // .withUser(accountCredentials.getUsername());
                        .passwordEncoder(passwordEncoder());


        // Create a default account

/*
        auth.jdbcAuthentication().dataSource(dataSource)
                .withDefaultSchema()
                .usersByUsernameQuery("select username,password from public.jkstest where username=? and password=?")
                .withUser(accountCredentials.getUsername());*/

        /*auth
            .userDetailsService(loginService)
            .passwordEncoder(passwordEncoder());*/
        //auth.userDetailsService(loginDetails);

       /* auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");*/
/*
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(users.username("user").password("password").roles("USER"))
                .withUser(users.username("admin").password("password").roles("USER","ADMIN"));*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

       PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
        return new BCryptPasswordEncoder();
    }


  /*  @Bean
     public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5432/SMOSS_DB");
          driver.setUsername("postgres");
          driver.setPassword("postgres");
//        driver.setUrl(intUrl);
//        driver.setUsername(userName);
//        driver.setPassword(password);
        return driver;
    }*/

}