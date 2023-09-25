package com.ezen.go90.domain.match.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ezen.go90.domain.match.dto.Participant;
import com.ezen.go90.domain.match.mapper.MatchMapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 참여 경기 통계를 위한 트랜잭션 처리 및 메서드 구현체
 *
 * @author 윤동진
 * @since  2023. 9. 21.
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class MatchServiceImpl implements MatchService {
	
	private final MatchMapper matchMapper;

	@Override
	public List<Participant> loadOnlyMatch() {
		
		List<Participant> list = matchMapper.loadMatchHistory();
		return list;
	}

	@Override
	public Map<String, List<Participant>> loadAllHistory(String memberId) {
		
		Map<String, List<Participant>> resultMap = new HashMap<>();
		
		
		List<Participant> playerStats = matchMapper.loadPlayerStat(memberId);
		
		  // 가져온 기록을 resultMap에 match_id를 key로 사용하여 매핑합니다.
	    for (Participant participant : playerStats) {
	        String matchId = String.valueOf(participant.getMatchId());
	        
	        // resultMap에서 해당 match_id의 리스트를 가져옵니다.
	        List<Participant> matchList = resultMap.getOrDefault(matchId, new ArrayList<>());
	        
	        // 현재 participant를 matchList에 추가합니다.
	        matchList.add(participant);
	        
	        // resultMap에 다시 매핑합니다.
	        resultMap.put(matchId, matchList);
	    }
		
		return resultMap;
	}
	
	
	
	
}
