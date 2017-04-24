package pl.lodz.uni.math.portalforprogrammers.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.lodz.uni.math.portalforprogrammers.service.UserService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   auth.jdbcAuthentication().dataSource(dataSource)
		   .usersByUsernameQuery(
		    "select nickname, password, enabled from users where nickname=?")
		   .authoritiesByUsernameQuery(
		    "select nickname, role from users where nickname=?")
		   .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/register").permitAll()
		.antMatchers("/**").hasRole("USER").and().formLogin().and().csrf().disable();
	}
}
