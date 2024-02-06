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
public class Vote {
	private int voteId;
	private String voteTitle;
	private String voteDate;
	private String voteContent;
	private String location;
	private String matchDate;
}
