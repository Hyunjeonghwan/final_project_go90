package com.ezen.go90.domain.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.go90.domain.article.dto.ArticleDTO;
import com.ezen.go90.domain.article.dto.Image;
import com.ezen.go90.domain.article.dto.ReplyDTO;
import com.ezen.go90.domain.common.web.PageParams;

/**
 * 
 * Article 테이블 관련 명세
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 8.
 */
@Mapper
public interface ArticleMapper {
   
   /** 신규 게시글 등록 */
   public void articleCreate(ArticleDTO articleDTO);
   
   /**   게시글 수정 */
   public void updateArticle(ArticleDTO articleDTO);
   
   /**게시판 번호 해당하는 게시글*/
   public List<ArticleDTO> findArticles(int boardId);
   
   /** 게시글 삭제 전 articleId 조정하기*/
   public void beforeRemoveUpdateArticleId(@Param("boardId")int boardId, 
                                           @Param("articleId")int articleId);
   
   /** 게시글 삭제*/
   public boolean removeArticle(int articleId);
   
   /** 댓글 삭제 */
   public boolean removeReply(int articleId);
   
   /**   게시판 상세보기*/
   public ArticleDTO openArticle(int articleId);
   
   /**   댓글 작성*/
   public void reply(ReplyDTO replyDTO);
   
   /** 페이징 계산에 필요한 게시글 전체 갯수 반환 (검색값 포함해서)*/
   public int getCountAll(PageParams pageParams);

   /** 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환*/
   public List<ArticleDTO> findByAll(PageParams pageParams);
   
   /**조회수 조절*/
   public void setHitcount(int articleId);
   
   /** 대댓글 달기 전 orderNo 조정하기*/
   public void updateOrderNo(@Param("boardId")int boardId, 
                         @Param("articleId")int articleId);
   /**   대댓글 작성*/
   public void replyComent(ReplyDTO replyDTO);
   
   /** 댓글 리스트 보여주기*/
   public List<ReplyDTO> replyShow(int articleId);
   
   /** 이미지 등록 */
   public void imageCreate(Image image);
   
   /** 이미지 목록 반환 */
    public List<Image> totalImageList();
}