package com.ezen.go90.domain.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Plan {
   
   //일정 아이디
   private int planId;
   
   //시작 날짜
   private String startDate;
   
   //종료 날짜
   private String endDate;
   
   //일정 제목
   private String planTitle;
   
   //일정 내용
   private String planContent;
   
   //장소
   private String planLocation;
   
}




