package com.innovationT.blog.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.security.auth.MemberPrincipalDetailService;
import com.innovationT.blog.security.auth.MemberPrincipalDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberAuthenticatorProvider implements AuthenticationProvider {
	
	@Autowired
	private MemberPrincipalDetailService principalDetailservice;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName(); // ID
		String password = (String) authentication.getCredentials(); //PASSWORD
		
		MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails) principalDetailservice.loadUserByUsername(username);
		
		//암호화 비밀번호 비교
		String dbPassword = memberPrincipalDetails.getPassword();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// 비밀번호 불일치
		if(!passwordEncoder.matches(password, dbPassword)) {
			log.info("비밀번호가 일치하지 않습니다 !");
			throw new BadCredentialsException("아이디 또는 비밀번호가 맞지 않습니다.");
		}
		
		// 계정 존재하지 않을때
		
		Member member = memberPrincipalDetails.getMember();
		if(member == null ||"N".equals(member.getMemUsed())) {
			log.info(" 사용 할 수 없는 계정입니다 !");
			throw new BadCredentialsException("사용 할 수 없는 계정입니다 !");
		}
		
		// 토큰 생성 후 반환
		return new UsernamePasswordAuthenticationToken(memberPrincipalDetails, memberPrincipalDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}