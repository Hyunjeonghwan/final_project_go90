package com.ezen.go90.domain.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.member.dto.MemberSearchCondition;

/**
 * 
 * @Project final_project_go90
 * @Author 현정환, 주성민
 * @Date 2023. 9. 8.
 */
@Mapper
public interface MemberMapper {
	
	/** 전체 회원 목록 반환 */
	public List<Member> findByAll();
	
	/** 회원 ID로 검색 */
	public Member findById(String id);
	
	/** 서비스 접근 제한을 위한 회원 검색 */
	public Member findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd, @Param("rank") String rank);
	
	/** 회원 생성 */
	public void create(Member member);
	
	/** 회원 정보 수정 */
	public void update(Member member);
	
	/** 탈퇴 회원 데이터 전송 */
	public void transfer(String memberId);
	
	/** 회원 탈퇴 */
	public void delete(String memberId);

}







