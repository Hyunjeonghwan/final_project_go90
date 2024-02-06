package com.ezen.go90.domain.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 출전 예상 명단의 회원(경기 설문에 참석으로 답한 회원)에 관련된 DTO.
 *
 * @author 윤동진
 * @since  2023. 9. 9.
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SpareMember {
   private String name;
   private String position;
   private String memberImg;
   private int backNumber;
   private int goal;
   private int assist;
   private int defence;
   private int save;
   private int voteId;
   private int voteResultId;
   private String matchDate;
}