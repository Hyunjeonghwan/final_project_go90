package com.ezen.go90.domain.article.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.go90.domain.article.dto.ArticleDTO;
import com.ezen.go90.domain.article.dto.Image;
import com.ezen.go90.domain.article.dto.ReplyDTO;
import com.ezen.go90.domain.common.web.PageParams;

/**
 * 
 * 게시판 관련 비즈니스 로직 및 트랜젝션 처리
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 11.
 */
@Service
public interface ArticleService {

   /** 게시글 작성 */
   public void regist(ArticleDTO articleDTO);

   /** 게시판 상세보기 */
   public ArticleDTO showArticle(int articleId);

   /** 게시글 수정 */
   public void updateArticle(ArticleDTO articleDTO);

   /** 게시글 삭제 */
   public boolean deleteArticle(int articleId);

   /** 게시판 번호 해당 게시글 */
   public List<ArticleDTO> findByBoardId(int boardId);

   /** 댓글 작성 */
   public ReplyDTO reply(ReplyDTO replyDTO);

   /** 댓글 리스트 보기 */
   public List<ReplyDTO> replyList(int articleId);

   /** 게시글 리스트 반환 (요청 페이지, 페이지당 보여지는 목록 갯수에 따른) */
   public List<ArticleDTO> getList(PageParams pageParams);

   /** 게시글 전체 개수 반환 */
   public int getArticleCount(PageParams pageParams);

   /** 이미지 등록 */
   public void registImg(Image image);

   /** 이미지 출력 */
   public List<Image> getImage();

}