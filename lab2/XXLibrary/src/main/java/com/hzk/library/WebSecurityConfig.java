package com.hzk.library;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select name,password,true from user where name=?")
		.authoritiesByUsernameQuery("select name,rolename from user,role where roleid=role.id and name=?");
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	             .antMatchers("/webjars/**","/asserts/**").permitAll()
	             .and()
            .authorizeRequests()
                .antMatchers("/","/logout","/main","/books/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
            .rememberMe()		//启用Remember功能
                .key("library_demo");
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//    	PasswordEncoder passwordEncoder =
//    		    PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    	String encodedPassword=passwordEncoder.encode("password");
//    	System.out.println(encodedPassword);
//        UserDetails user =
//             User  //.withDefaultPasswordEncoder()
//                //.username("user")
//              .withUsername("user1")  
//             .password(encodedPassword)
//                .roles("USER")
//                .build();
//System.out.println(user.getPassword());
//    	return new InMemoryUserDetailsManager(user);      
//    }
}