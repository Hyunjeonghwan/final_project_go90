package com.ezen.go90.domain.match.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.go90.domain.match.dto.Participant;


/**
 * 
 * 경기 참여 회원 기능 관련 DB처리를 위한 메서드 명세
 *
 * @author 윤동진
 * @since  2023. 9. 20.
 * @version 1.0
 */
@Mapper
public interface MatchMapper {
	//아이디로 조회하는 선수의 경기, 쿼터 별 모든 스텟
	public List<Participant> loadPlayerStat(String memberId);
	
	//전체 히스토리 조회
	public List<Participant> loadAllHistory(Participant participant);
	
	//경기정보 조회
	public List<Participant> loadMatchHistory();


}

