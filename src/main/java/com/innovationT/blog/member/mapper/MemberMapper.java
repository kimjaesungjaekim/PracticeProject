package com.innovationT.blog.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovationT.blog.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Repository
@Mapper
public interface MemberMapper {
	
	/**
	 * 스프링시큐리티 인증인가 세부정보
	 * @param memLoginId
	 * @return
	 */
	@Select("    	SELECT     MEM_LOGIN_ID "
			+ "				  ,MEM_PW"
			+ "				  ,MEM_NM "
			+ "				  ,MEM_BIRTH_DATE "
			+ "				  ,MEM_NICK "
			+ "				  ,MEM_TELNO "
			+ "				  ,MEM_EMAIL "
			+ "				  ,MEM_ADRES1 "
			+ "				  ,MEM_ADRES2 "
			+ "				  ,MEM_ZIP "
			+ "				  ,MEM_ROLE "
			+ "				  ,MEM_USED "
			+ "				  ,MEM_DEL "
			+ "				  ,MEM_IN_DATE"
			+ "				  ,MEM_UP_DATE"
			+ "				  ,FILE_NO"
			+ "		FROM      MEMBER"
			+ "		WHERE     MEM_LOGIN_ID = #{memLoginId}"
			)
	public Member selectUserInfo(String username);
	
	@Select("SELECT * FROM MEMBER WHERE MEM_LOGIN_ID = #{userId}")
	public Member selectUser(@Param("userId")String userId);
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	@Insert("INSERT INTO `member`"
			+ "("
			+ "    `MEM_LOGIN_ID`,"
			+ "    `MEM_PW`,"
			+ "    `MEM_NM`,"
			+ "    `MEM_BIRTH_DATE`,"
			+ "    `MEM_NICK`,"
			+ "    `MEM_TELNO`,"
			+ "    `MEM_EMAIL`,"
			+ "    `MEM_ADRES1`,"
			+ "    `MEM_ADRES2`,"
			+ "    `MEM_ZIP`,"
			+ "    `MEM_ROLE`,"
			+ "    `MEM_USED`,"
			+ "    `MEM_DEL`,"
			+ "    `MEM_IN_DATE`,"
			+ "    `MEM_UP_DATE`,"
			+ "    `FILE_NO`"
			+ ")"
			+ "VALUES"
			+ "("
			+ "    #{memLoginId},"
			+ "    #{memPw},"
			+ "    #{memNm},"
			+ "    #{memBirthDate},"
			+ "    #{memNick},"
			+ "    #{memTelno},"
			+ "    #{memEmail},"
			+ "    #{memAdres1},"
			+ "    #{memAdres2},"
			+ "    #{memZip},"
			+ "    #{memRole},"
			+ "    #{memUsed},"
			+ "    #{memDel},"
			+ "    #{memInDate},"
			+ "    #{memUpDate},"
			+ "    #{fileNo}"
			+ ")"
			
			)
	public int insertNewUser(Member member);
	
	/**
	 * 회원정보 수정
	 * @param member
	 * @return
	 */
	public int updateUser(Member member);
	
	/**
	 * 회원탈퇴
	 * @param memLoginId
	 * @return
	 */
	public int deleteUser(String memLoginId);
}