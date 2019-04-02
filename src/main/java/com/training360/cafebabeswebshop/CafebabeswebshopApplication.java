package com.training360.cafebabeswebshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.sql.DataSource;

@SpringBootApplication
@EnableWebSecurity
@Configuration

public class CafebabeswebshopApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(CafebabeswebshopApplication.class, args);
	}



	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/js/**", "/css/**").permitAll()
				.antMatchers("/basket.html", "/myorders.html").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/adminproducts.html", "/adminusers.html", "/users.html", "/dashboard.html", "/reports.html", "/orders.html", "/users", "/orders", "/reports", "/dashboard", "/categories.html", "/upload.html").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/login.html").loginProcessingUrl("/login")
				.defaultSuccessUrl("/index.html")
				.and()
				.logout();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource, PasswordEncoder passwordEncoder) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select user_name,password,enabled from users where user_name=?")
				.authoritiesByUsernameQuery("select user_name, role from users where user_name = ?");
	}

}
