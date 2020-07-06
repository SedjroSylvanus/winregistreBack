package com.dgi.dsi.winregistre.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception
//	{ return super.authenticationManagerBean(); }

	@Primary
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Resource
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		     //Pour deactiver la securit� par session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin();
//		http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
		http.authorizeRequests().antMatchers("/login/**","/register/**","/quittancePDF/**",
				"/report/**","/repportSommier/**")
		.permitAll();

		http.authorizeRequests().antMatchers("/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/configuration/**",
				"/webjars/**",
				"/public",
				"/h2-console/**/**",
				"/api/auth/signup/**",
				"/calculPaiementAss/**",
				"/listCommunes/**",
				"/dateServeur/**",
				"/listContribuables/**",
				"/listNatureActes/**",
				"/lastNumberActesBordereau/**",
				"/lastNumberActesBordereau/**",
				"/getNatureActeByCode/**",
				"/getContribuableByIfu/**",
				"/ajoutActeContribuable/**",
				"/reportPReliquidation/**",
				"/listActeAudit/**",
				"/oneBordereauActeByNumero/**",
				"/reportPreliquidation/**",




				"/api/auth/signin/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/contacts/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
