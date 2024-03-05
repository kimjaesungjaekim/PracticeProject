package com.innovationT.blog.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.innovationT.blog.common.enumpkg.ServiceResult;
import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	
	
	private final MemberMapper mapper;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public Member retrieveUserInfo(String memLoginId) {
		Member member = mapper.selectUserInfo(memLoginId);
		
		return member;
	}
	
	@Override
	public ServiceResult createNewUser(Member member){
		
		int cnt = 0;
		ServiceResult res;
		Member members = null ;
		
		String inputPassword = member.getMemPw();
		String encodePassword = passwordEncoder.encode(inputPassword);
		member.setMemPw(encodePassword);
		
		LocalDateTime inDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		
		String formattedDate = inDate.format(formatter);
		
		member.setMemInDate(formattedDate);
		member.setMemRole("MEMBER");
		member.setMemUsed("Y");
		member.setMemDel("N");
		
		try {
			cnt = mapper.insertNewUser(member);
			members = mapper.selectUserInfo(member.getMemLoginId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else if(members.getMemLoginId().equals(member.getMemLoginId())) {
			 res = ServiceResult.DUPLICATED;
		}else {
			res = ServiceResult.FAIL;
		}
		
		return res;
		
	}

	@Override
	public ServiceResult modifyUser(Member member) {
		int cnt = 0;
		ServiceResult res;
		try {
			cnt = mapper.updateUser(member);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else {
			 res = ServiceResult.FAIL;
		}
		return res;
	}

	@Override
	public ServiceResult removeUser(String memLoginId) {
		int cnt = 0;
		ServiceResult res;
		try {
			cnt = mapper.deleteUser(memLoginId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else {
			 res = ServiceResult.FAIL;
		}
		return res;
	}

	


}