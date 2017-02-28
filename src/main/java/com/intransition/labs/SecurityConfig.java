package com.intransition.labs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http	.authorizeRequests()
        			.antMatchers("/resources/**").permitAll()
        			//.antMatchers("/add**").permitAll()
        			.antMatchers("/personal/**").not().anonymous()
        			.antMatchers("/admin/**").access("hasAuthority('ADMIN')")
        			.and()
        			.csrf().disable()
    			.formLogin()
					.loginPage("/auth")
					.failureUrl("/auth?error")
					.loginProcessingUrl("/auth/authorize")
    				.usernameParameter("username")
    				.passwordParameter("password")
    				.defaultSuccessUrl("/")
    				.permitAll()
    				.and()
    			.logout()
    				.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
    				.logoutSuccessUrl("/")
    				.deleteCookies( "JSESSIONID","remember-me", "theme" )
    				.permitAll()
    				.and()
    			.rememberMe()
    				.tokenValiditySeconds(86400)
    				.rememberMeParameter("remember-me")
    				.tokenRepository(persistentTokenRepository());
    			//.exceptionHandling()http; 
    }				

    @Autowired
	DataSource dataSource;
        
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder( bCryptPasswordEncoder() );
    } 
    
}
