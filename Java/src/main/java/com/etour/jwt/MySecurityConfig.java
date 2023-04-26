package com.etour.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired JWTAuthenticationFilter jwtfilter;
	 
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}
	
	
	
	
     @Bean	
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	 {
    	 System.out.println("securityFilterChain method");
    	 http.cors().and().csrf()
    	 	.disable() 
    	 	.csrf() 
    	 	.disable() 
    	 	.authorizeRequests()
    	 .antMatchers("/token","/api/admin/**","/api/booking","/api/invoice","/api/itinerary/{id}","/api/categoriessub/{categoryid}","/api/categories","/api/search","/api/customers","/register").permitAll() .anyRequest().authenticated() .and()
    	 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	 
    	 http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    	 
    	 http.authenticationProvider(daoAuthenticationProvider());
    	 DefaultSecurityFilterChain build=http.build();
    	 return build;
	 }
	
	  @Bean public PasswordEncoder passwordEncoder() 
	  {
	  System.out.println("inside passwordEncoder method"); 
	  return new BCryptPasswordEncoder(); 
	  }
	 
	
	  @Bean 
	  public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws
	  Exception { System.out.println("inside authenticationManagerBean method");
	  return configuration.getAuthenticationManager(); }
	  
	  @Bean
	  public DaoAuthenticationProvider daoAuthenticationProvider()
	  {
		  System.out.println("inside daoAuthenticationProvider method");
		  DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		  provider.setUserDetailsService(customUserDetailsService);
		  provider.setPasswordEncoder(passwordEncoder());
		  return provider;
	  }
}
