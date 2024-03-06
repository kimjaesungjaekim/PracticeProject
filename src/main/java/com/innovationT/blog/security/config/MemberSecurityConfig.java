package com.innovationT.blog.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.innovationT.blog.security.auth.MemberPrincipalDetailService;
import com.innovationT.blog.security.provider.MemberAuthenticatorProvider;

@Configuration
@EnableWebSecurity
public class MemberSecurityConfig {
	
	// 생성한 provider 주입 service 로직 처리 및 인증 처리
	@Autowired
	MemberAuthenticatorProvider provider;
	
	//로그인 기억 저장 provider 내부에 service 처리
	@Autowired
	MemberPrincipalDetailService service;
	
	//AuthenticationManager 생성
	@Autowired
	public void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.authenticationProvider(provider);
	}
	
	
	//SecurityFiterChain 생성 구현
	@Bean
	public SecurityFilterChain memberSecurityFilterChain (HttpSecurity http) throws Exception {
		
		    http
		    	.csrf().disable();
		    
		    http.authorizeRequests(authorize -> {
		        try {
		            authorize
		                    .requestMatchers(
		                    		new AntPathRequestMatcher("/login/loginForm"),
//		                    		new AntPathRequestMatcher("/login/**"),
		                    		new AntPathRequestMatcher("/signUp/**"),
		                            new AntPathRequestMatcher("/css/**"),
		                            new AntPathRequestMatcher("/files/**"),
		                            new AntPathRequestMatcher("/images/**"),
		                            new AntPathRequestMatcher("/js/**"),
		                            new AntPathRequestMatcher("/attachment/**")
			                    ).permitAll() 
			                .requestMatchers(
			                        new AntPathRequestMatcher("/board/**")
			                    ).hasRole("MEMBER") 
		                    .and()
			                    .formLogin()
		                        .loginPage("/member/login/loginForm") 
		                        .loginProcessingUrl("/member/login/login") 
		                        .defaultSuccessUrl("/board/boardList")
		                        .usernameParameter("memLoginId")
		                        .passwordParameter("memPw")
		                        .successHandler(new MemberAuthSuccessHandler()) 
		                        .failureHandler(new MemberAuthFailureHandler())
	                        .permitAll()
		                    .and()
			                    .logout()
			                    .logoutUrl("/login/logout")
			                    .logoutSuccessUrl("/login/loginForm?logout=1")
			                    .deleteCookies("JSESSIONID"); 
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    });
		    
		    http.rememberMe()
		    	.key("innovation")
		    	.tokenValiditySeconds(60*60*24)
		    	.userDetailsService(service)
		    	.rememberMeParameter("rememberMe");
		    
		    return http.build();
		    
	}
	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}