package com.innovationT.blog.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


	public class MemberAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest req ,HttpServletResponse resp , Authentication authentication
			)throws ServletException , IOException {
			
			setDefaultTargetUrl("/board");
			
			super.onAuthenticationSuccess(req, resp, authentication);
		}
}