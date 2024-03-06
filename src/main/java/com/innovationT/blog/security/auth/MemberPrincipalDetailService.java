package com.innovationT.blog.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MemberPrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = mapper.selectUser(username);
		
		log.info("로그인한 아이디 : {}",username);
		log.info("로그인한 계정 정보 : {}",member);
		
		if(member == null) {
			throw new UsernameNotFoundException(username + "아이디의 계정 정보를 찾을 수 없습니다.");
		}
		
		if(member.getMemUsed().equals("N")) {
			throw new UsernameNotFoundException(username + ": 아이디의 계정은 사용할 수 없는 계정 입니다.");
		}
		
		if(member.getMemDel().equals("Y")) {
			throw new UsernameNotFoundException(username + ": 아이디의 계정은 삭제된 계정 입니다.");
		}
		
		return new MemberPrincipalDetails(member);
	}

}