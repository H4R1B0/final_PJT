package com.ssafy.traveler.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

	private String memberId;
	private String memberName;
	private String memberPassword;
	private String email;
	private String memberSalt;

}
