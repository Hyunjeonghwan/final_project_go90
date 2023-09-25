package com.ezen.go90.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @Project final_project_go90
 * @Author 현정환, 주성민
 * @Date 2023. 9. 11.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberSearchCondition {
	private String memberId;
	private String name;
	private String email;
}
