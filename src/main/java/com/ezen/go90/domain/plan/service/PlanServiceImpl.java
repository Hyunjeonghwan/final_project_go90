package com.ezen.go90.domain.plan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.go90.domain.plan.dto.Plan;
import com.ezen.go90.domain.plan.mapper.PlanMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlanServiceImpl implements PlanService{
   

      private final PlanMapper planMapper;

      @Override
      public List<Plan> getAllPlans() {
          List<Plan> plans = planMapper.getAllPlans();
          System.out.println("Fetched plans from the database: " + plans);
          return plans;
      }


   @Override
   public Plan getPlanById(int planId) {
      return planMapper.getPlanById(planId);
   }

   @Override
   public void addPlan(Plan plan) {
      try {
         planMapper.addPlan(plan);
      }catch (Exception e) {
         log.warn("addPlan() 오류 : {}", e);
      }
   }
   
   @Override
   public void updatePlan(Plan plan) {
      planMapper.updatePlan(plan);
      
   }

   @Override
   public void deletePlan(Plan plan) {
      try {
      planMapper.deletePlan(plan);
      }catch (Exception e) {
         log.warn("deletePlan() 오류 : {}", e);
      }
   }

}