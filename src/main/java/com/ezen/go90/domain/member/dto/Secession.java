package com.ezen.go90.domain.member.dto;

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
public class Secession {
	private int secessionId;
	private String memberId;
	private String secessionName;
	private String secessionNumber;
	private String secessionBirth;
	private String secessionDate;
}





