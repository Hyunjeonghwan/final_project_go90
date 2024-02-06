package com.ezen.go90.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 경기 승무패, 득점 관련 DTO
 *
 * @author 윤동진
 * @since  2023. 9. 20.
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Match {
	private int matchId;
	private int homeMatchScore;
	private int awayMatchScore;
	private String team;
	private String opponent;
	private String result;
	private String match_date;
	
	private int quarterId;
	private int homeQuarterScore;
	private int awayQuarterScore;
	private String quarterName;
	private String quarterResult;
}
