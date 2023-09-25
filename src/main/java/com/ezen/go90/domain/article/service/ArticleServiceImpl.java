package com.ezen.go90.domain.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.go90.domain.article.dto.ArticleDTO;
import com.ezen.go90.domain.article.dto.Image;
import com.ezen.go90.domain.article.dto.ReplyDTO;
import com.ezen.go90.domain.article.mapper.ArticleMapper;
import com.ezen.go90.domain.common.web.PageParams;
import com.ezen.go90.domain.common.web.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 게시글 관련 비즈니스 로직 처리 및 트랜잭션 관리 구현체
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 11.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

   private Pagination page;
   private final ArticleMapper articleMapper;

   /** 게시판 CRUD */

   @Override
   @Transactional
   public void regist(ArticleDTO articleDTO) {
      articleMapper.articleCreate(articleDTO);
   }

   @Override
   @Transactional
   public ArticleDTO showArticle(int articleId) {
      ArticleDTO article = articleMapper.openArticle(articleId);
      List<ReplyDTO> replyList = articleMapper.replyShow(articleId);
      articleMapper.setHitcount(articleId);
      article.setReplyList(replyList);
      return article;

   }

   @Override
   @Transactional
   public void updateArticle(ArticleDTO articleDTO) {
      articleMapper.updateArticle(articleDTO);
   }

   @Override
    @Transactional
    public boolean deleteArticle(int articleId) {
       ArticleDTO articleDTO = ArticleDTO.builder().articleId(articleId).build();
       boolean result = false;
       if(articleId == articleDTO.getArticleId()) {
       articleMapper.removeReply(articleId);
       articleMapper.removeArticle(articleId);
       result = true;
       }
       return result;
    }

   @Override
   @Transactional
   public List<ArticleDTO> findByBoardId(int boardId) {
      List<ArticleDTO> list = articleMapper.findArticles(boardId);
      return list;
   }

   @Override
   @Transactional
   public int getArticleCount(PageParams pageParams) {
      return articleMapper.getCountAll(pageParams);
   }

   @Override
   @Transactional
   public List<ArticleDTO> getList(PageParams pageParams) {
      return articleMapper.findByAll(pageParams);
   }

   @Override
   @Transactional
   public ReplyDTO reply(ReplyDTO replyDTO) {

      int levelNo = replyDTO.getLevelNo();
      int articleId = replyDTO.getArticleId();

      if (levelNo == 0) {
         articleMapper.reply(replyDTO);
      } else if (levelNo >= 1) {
         // 댓글인 경우
         // 1. 해당 레벨의 댓글들의 orderNo를 증가
         articleMapper.updateOrderNo(levelNo, articleId);
         // 2. 댓글 추가
         articleMapper.replyComent(replyDTO);
      }
      return replyDTO;
   }
   
   @Override
   @Transactional
   public List<ReplyDTO> replyList(int articleId) {
      return articleMapper.replyShow(articleId);
   }

   @Override
   @Transactional
   public void registImg(Image image) {
      articleMapper.imageCreate(image);
   }
   
   @Override
   @Transactional
   public List<Image> getImage() {
      return articleMapper.totalImageList();
   }
}