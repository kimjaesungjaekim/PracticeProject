package com.innovationT.blog.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {
	
	
	@NotBlank
	private String memLoginId;
	
	@NotBlank
	private String memPw;
	
	@NotBlank
	private String memNm;
	
	@NotBlank
	private String memBirthDate;
	
	private String memNick;
	
	@NotBlank
	private String memTelno;
	
	private String memEmail;
	
	@NotBlank
	private String memAdres1;
	
	private String memAdres2;
	
	@NotBlank
	private String memZip;
	
	private String memRole;
	
	private String memUsed;
	
	private String memDel;
	
	private String memInDate;
	
	private String memUpDate;
	
	private String fileNo;
}