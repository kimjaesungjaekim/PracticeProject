package com.innovationT.blog.security.config;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class MemberAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest req ,HttpServletResponse resp , AuthenticationException exception
		)throws ServletException , IOException {
			
		HttpSession session = req.getSession();
		
		session.setAttribute("Login Error", exception.getMessage());
		
		setDefaultFailureUrl("/login/loginForm?error=true&t=h");
		
		super.onAuthenticationFailure(req, resp, exception);
	}
}