package com.innovationT.blog.member.service;

import com.innovationT.blog.common.enumpkg.ServiceResult;
import com.innovationT.blog.member.entity.Member;

public interface MemberService {
	
	/**
	 * 스프링시큐리티 인증인가 세부정보
	 * @param memLoginId
	 * @return
	 */
	public Member retrieveUserInfo(String memLoginId);
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public ServiceResult createNewUser(Member member);
	
	/**
	 * 회원정보 수정
	 * @param member
	 * @return
	 */
	public ServiceResult modifyUser(Member member);
	
	/**
	 * 회원탈퇴
	 * @param memLoginId
	 * @return
	 */
	public ServiceResult removeUser(String memLoginId);
}