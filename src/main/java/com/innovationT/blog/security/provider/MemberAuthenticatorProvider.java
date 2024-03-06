package com.innovationT.blog.security.provider;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAuthenticatorProvider implements AuthenticationProvider {
	
	private final MemberPrincipalDetailService principalDetailservice;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		log.info("authentication 정보 확인 : {}" ,authentication);
		
		String username = authentication.getName(); // ID
		String password = (String) authentication.getCredentials(); //PASSWORD
		
		log.info("인증 거친 비밀 번호 체크 일치 불일치 체크 전 아이디와 비밀번호 ! : 아이디 : {}  비밀번호 : {}" , username, password);
		
		MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails)principalDetailservice.loadUserByUsername(username);
		
		//암호화 비밀번호 비교
		String dbPassword = memberPrincipalDetails.getPassword();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// 비밀번호 불일치
		if(! passwordEncoder.matches(password, dbPassword)) {
			log.info("비밀번호가 일치하지 않습니다 !");
			throw new BadCredentialsException("아이디 또는 비밀번호가 맞지 않습니다.");
		}
		
		log.info("인증 거친 비밀 번호 체크 일치 불일치 체크 후 ! : 입력번호 -{}  저장된번호 -{}" , password,dbPassword);
		
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