package com.ezen.go90.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Member {
	//@NotBlank(message = "아이디는 필수 입력 항목입니다.")
	//@Size(min = 6, max = 12)
	private String memberId;
	@NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
	private String passwd;
	@NotBlank(message = "이름은 필수 입력 항목입니다.")
	private String name;
	@NotBlank(message = "이메일은 필수 입력 항목입니다.")
	private String email;
	private String birth;
	private String phonenumber;
	private String regdate;
	private String rank;
	private String position;
	private int backnumber;
	private String status;
	private String goal;
	private String assist;
	private String defence;
	private String save;
	private String memberImg;
}





