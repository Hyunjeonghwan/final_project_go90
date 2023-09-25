package com.ezen.go90.domain.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ezen.go90.domain.match.dto.Participant;

/**
 * 
 * 경기관련 통계 추상메서드 명세 및 트랜잭션 처리 
 *
 * @author 윤동진
 * @since  2023. 9. 21.
 * @version 1.0
 */
@Service
public interface MatchService {
	
	//경기 정보 조회
	public List<Participant> loadOnlyMatch();
	
	//선수의 개별 기록 조회
	public Map<String, List<Participant>> loadAllHistory(String memberId);
	
}
