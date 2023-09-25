package com.ezen.go90.domain.plan.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.plan.dto.Plan;

@Mapper
public interface PlanMapper {
   
   // 모든 일정 리스트 조회
    public List<Plan> getAllPlans();

    // 특정 일정 조회
    public Plan getPlanById(@Param("planId") int planId);

    // 일정 추가
    public void addPlan(Plan plan);

    // 일정 수정
    public void updatePlan(Plan plan);

    // 일정 삭제
    public void deletePlan(Plan plan);
    

}






