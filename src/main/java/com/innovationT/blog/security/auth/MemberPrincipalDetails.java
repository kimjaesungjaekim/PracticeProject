package com.innovationT.blog.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innovationT.blog.member.entity.Member;

@SuppressWarnings("serial")
public class MemberPrincipalDetails implements UserDetails {
	
	private final Member member;
	
	public MemberPrincipalDetails(Member member) {
		this.member=member;
	}
	
	public Member getMember() {
		return member;
	}
	
	// 계정 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(member.getMemRole()));
		
		return authorities;
	}
	
	// 비밀번호
	@Override
	public String getPassword() {
		
		return member.getMemPw();
	}
	
	// 아이디
	@Override
	public String getUsername() {
		
		return member.getMemLoginId();
	}
	
	//계정 만료 여부 (true: 만료 X , false : 만료 O )
	@Override
	public boolean isAccountNonExpired() {
		
			return true;
	}
	
	// 계정 잠금 여부 (true: 잠김 X , false : 잠김 O )
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	
	// 계정 비밀번호 만료 여부 (true: 만료 X , false : 만료 O )
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	// 계정 활성화 여부 (true: 활성화 O , false : 활성화 X )
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	

}