package com.ezen.go90.domain.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @Project final_project_go90
 * @Author 현정환
 * @Date 2023. 9. 18.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VoteResult {
	private int voteResultId;
	private String attend;
	private int voteId;
	private String memberId;
}
