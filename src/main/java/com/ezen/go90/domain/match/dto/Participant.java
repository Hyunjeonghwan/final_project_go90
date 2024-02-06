package com.ezen.go90.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 실제 경기 참여 회원과 회원 별 스탯 관리 DTO
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
public class Participant {
	private int participantId;
	private int quarterId;
	private int matchId;
	private String matchDate;
	private int teamMatchScore;
	private int opopnentMatchScore;
	private String team;
	private String opponent;
	private String result;
	private String quarterName;
	private String quarterResult;	
	private int teamQuarterScore;
	private int opponentQuarterScore;
	private String memberId;
	private String name;    //DB에는 없는데 혹시몰라서 추가
	private String position;
	private String backNumber;
	private int goal;
	private int assist;
	private int tackle;
	private int save;
	


	
	

	
//	private Map<String, Object> matchData;
	
	
	
}
