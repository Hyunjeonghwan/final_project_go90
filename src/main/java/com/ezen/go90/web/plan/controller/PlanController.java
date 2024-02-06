package com.ezen.go90.web.plan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.plan.dto.Plan;
import com.ezen.go90.domain.plan.service.PlanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 일정관리
 *
 * @author 주성민
 * @since 2023. 9. 4.
 * @version 1.0
 * @see com.ezen.go90.domain.plan.service.PlanService
 * @see com.ezen.go90.domain.plan.service.PlanServiceImpl
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class PlanController {
   
   private final PlanService planService;
   
   @GetMapping("/plan")
    public String showPlanForm(Model model ,HttpServletRequest request) {
      
      HttpSession session = request.getSession(false);
         Member member = (Member) session.getAttribute("loginMember");
         String rank = member.getRank();
         log.info(rank);
      
        List<Plan> plans = planService.getAllPlans();
        model.addAttribute("plans", plans); // 일정 목록을 모델에 추가
        model.addAttribute("plan", new Plan()); // 새 일정을 추가할 때 사용할 빈 Plan 객체 추가
        return "schedule/plan";
    }
   
   @PostMapping("/plan")
   @ResponseBody
   public List<Plan> addPlans(@RequestBody  Plan plan) {
      log.info("수신한 일정정보 {}", plan.toString());
      
          planService.addPlan(plan);
          List<Plan> plans = planService.getAllPlans();
          return plans;
       
   }
   
   @PostMapping("/plan/delete")
   @ResponseBody
   public List<Plan> deletePlan(@RequestBody Plan plan) {
          planService.deletePlan(plan);
          List<Plan> plans = planService.getAllPlans();
          return plans;
       
   }
   @PostMapping("/plan/update")
   @ResponseBody
   public List<Plan> updatePlan(@RequestBody Plan plan) {
          planService.updatePlan(plan);
          List<Plan> plans = planService.getAllPlans();
          return plans;
       
   }
}