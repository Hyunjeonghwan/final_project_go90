package com.ezen.go90.domain.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.plan.dto.Plan;
import com.ezen.go90.domain.plan.mapper.PlanMapper;

/**
 * 일정 관련 비즈니스 로직 처리 및 트랜잭션 관리
 */
public interface PlanService {
   
    public List<Plan> getAllPlans();

    public Plan getPlanById(int planId);

    public void addPlan(Plan plan);

    public void updatePlan(Plan plan);

    public void deletePlan(Plan plan);
}