package com.ezen.go90.web.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.go90.domain.article.dto.ArticleDTO;
import com.ezen.go90.domain.article.dto.Image;
import com.ezen.go90.domain.article.dto.ReplyDTO;
import com.ezen.go90.domain.article.service.ArticleService;
import com.ezen.go90.domain.board.dto.BoardDTO;
import com.ezen.go90.domain.board.service.BoardService;
import com.ezen.go90.domain.common.web.PageParams;
import com.ezen.go90.domain.common.web.Pagination;
import com.ezen.go90.domain.member.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 사용자 관련 웹 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @Project final_project_go90
 * @Author 장원종
 * @Date 2023. 9. 11.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
   
   private static final int elementSize = 10;
   private static final int pageSize = 5;

   private final BoardService boardService;
   private final ArticleService articleService;
   
   @Value("${common.uploadPath}")
   private String location;

   // 쿼리스트링 = RequestParam , th:href~~{}() = pathVariable

   /** 게시판 리스트 반환 처리 */
   @GetMapping("/{boardId}")
   public String find(@PathVariable("boardId") int boardId,
                    @RequestParam(value= "page", required = false, defaultValue = "1") int page, 
                      @RequestParam(value="type", required = false) String type,
                      @RequestParam(value="input",required = false) String input,
                    Model model) {
      
      int rowCount = 0;
      PageParams pageParams = PageParams.builder()
                                .boardId(boardId)
                                .input(input)
                                .type(type)
                                .build();
      rowCount = articleService.getArticleCount(pageParams);
      pageParams.setBoardId(boardId);
      pageParams.setElementSize(elementSize);
      pageParams.setPageSize(pageSize);
      pageParams.setRowCount(rowCount);
      pageParams.setRequestPage(page);

      String viewName = "board/list";
      Pagination pagination = new Pagination(pageParams);
    
      List<BoardDTO> board = boardService.load();
      List<BoardDTO> boards = boardService.searchByBoardId(boardId);
      List<ArticleDTO> articles= articleService.getList(pageParams);
      List<ArticleDTO> article = articleService.findByBoardId(boardId);
      List<Image> images = articleService.getImage();
      
      model.addAttribute("images", images);
      model.addAttribute("article", article);
      model.addAttribute("pagination", pagination);
      model.addAttribute("pageParams", pageParams);
       model.addAttribute("type", pageParams.getType());
       model.addAttribute("input", pageParams.getInput());
       model.addAttribute("board", board);
      model.addAttribute("boards", boards);
      model.addAttribute("articles", articles);

      if (boardId == 2 && boardId == 4) {
         viewName = "board/list";
      } else if (boardId == 1) {
         viewName = "board/notice";
      } else if (boardId == 3) {
         viewName = "board/gallery";
      } 
      return viewName;
   }
   
   @GetMapping("/gallery/{imageName}")
    @ResponseBody
    public ResponseEntity<Resource> showProfile(@PathVariable String imageName) throws IOException {

       Path path = Paths.get(location + "/" + imageName);
       String contentType = Files.probeContentType(path);
       Resource resource = new FileSystemResource(path);
       HttpHeaders headers = new HttpHeaders();
       headers.add(HttpHeaders.CONTENT_TYPE, contentType);
       
       return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

   /** 게시글 작성 페이지 이동 */
   @GetMapping("/write")
   public String write(Model model, ArticleDTO articleDTO, BoardDTO boardDTO) {
      
      Image image = new Image();
      model.addAttribute(image);
      
      model.addAttribute("articleDTO", articleDTO);
      model.addAttribute("boardDTO", boardDTO);
      return "board/article/write";
   }

   /** 게시글 작성 처리 */
   @PostMapping("/write")
   public String regist(@RequestParam("boardId") int boardId, @ModelAttribute("articleDTO") ArticleDTO articleDTO, @ModelAttribute("image") Image image,
         @RequestParam("memberId") String memberId, RedirectAttributes redirectAttributes, @RequestParam("images") MultipartFile[] images, Model model) {
      
       articleDTO.setArticleWriter(memberId);
      articleDTO.setBoardId(boardId);
      
      boolean isFirstImage = true;
      
      String uploadDir = "C:/ezen-fullstack/workspace/final_project_go90/upload/";
      // 이미지 업로드 및 연결 처리
       for (MultipartFile file : images) {
           if (!file.isEmpty()) {
              try {
                 UUID uuid = UUID.randomUUID();
                  String originalFileName = file.getOriginalFilename();
                  String fileName = uuid.toString() + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
                  
                  File upload = new File(uploadDir, fileName);
                  
               file.transferTo(upload);
            
               if (isFirstImage) {
                      articleDTO.setImagePath(fileName);
                      articleService.regist(articleDTO);
                      isFirstImage = false;
                  }
                  image.setImgPath(fileName);
                  articleService.registImg(image);
              }
              catch (IOException e) {
                 e.printStackTrace();
              }
           }else {
              articleService.regist(articleDTO);
           }
       }
      
       
      model.addAttribute("articleDTO", articleDTO);
      redirectAttributes.addFlashAttribute("boardId", boardId);
      
      return "redirect:/board/" + boardId;
   }
   
   /* 공지사항 게시글작성 이동 */
   @GetMapping("/noticewrite")
   public String faqwrite(Model model, 
                     ArticleDTO articleDTO,
                     BoardDTO boardDTO) {
      
      model.addAttribute("articleDTO", articleDTO);
      model.addAttribute("boardDTO", boardDTO);
      return "board/article/noticewrite";
   }
   
   /* 공지사항 게시글작성 처리 */
   @PostMapping("/noticewrite")
   public String faqRegist(@RequestParam("boardId") int boardId, 
                     @ModelAttribute("articleDTO") ArticleDTO articleDTO,
                     @RequestParam("memberId") String memberId, 
                     RedirectAttributes redirectAttributes, 
                     Model model) {

      articleDTO.setArticleWriter(memberId);
      articleDTO.setBoardId(boardId);
      articleService.regist(articleDTO);
      model.addAttribute("articleDTO", articleDTO);
      redirectAttributes.addFlashAttribute("boardId", boardId);
      return "redirect:/board/" + boardId;
   }

   /* 게시글 상세보기 처리 */
      @GetMapping("/details/{articleId}/{hitcount}")
      public String showArticleDetails(@PathVariable("articleId") int articleId,
                               @ModelAttribute("articleDTO") ArticleDTO articleDTO, 
                               @PathVariable("hitcount") int hitcount, 
                               Model model,
                               HttpSession session) {
         // 게시글 상세정보와 댓글 목록 가져오기
         ArticleDTO detailArticle = articleService.showArticle(articleId);
         model.addAttribute("article", detailArticle);
         // 현재 로그인한 사용자의 정보
         Member loginMember = (Member) session.getAttribute("loginMember");
         // 만약 로그인한 사용자의 rank가 "감독"인 경우 또는 게시물의 작성자인 경우
//         if (loginMember != null && ("감독".equals(loginMember.getRank())
//               || loginMember.getMemberId().equals(articleDTO.getArticleWriter()))) {
//            model.addAttribute("canEdit", true);
//         } else {
//            model.addAttribute("canEdit", false);
//         }
         return "board/article/articledetail";
   }
   
   /* 게시글 수정 화면 */
   @GetMapping("/updatewrite/{boardId}/{articleId}")
   public String update(@PathVariable("boardId") int boardId, 
                   @PathVariable("articleId") int articleId, 
                   Model model) {
      
      ArticleDTO articleDTO = articleService.showArticle(articleId);
      articleDTO.setBoardId(boardId);
      model.addAttribute("article", articleDTO);
      return "board/article/updatewrite";
   }

   /* 게시글 수정 처리 */
   @PostMapping("/updatewrite")
   public String updateArticle(@ModelAttribute("articleDTO") ArticleDTO articleDTO, @RequestParam("boardId") int boardId,
         RedirectAttributes redirectAttributes, Model model) {
      
      articleDTO.setBoardId(boardId);
      articleService.updateArticle(articleDTO);
      
      model.addAttribute("boardId", boardId);
      redirectAttributes.addFlashAttribute("boardId", boardId);
      return "redirect:/board/" + boardId;
   }

   /* 게시글 삭제 */
   @GetMapping("/delete/{articleId}")
   public String remove(@PathVariable("articleId") int articleId, Model model) {
      ArticleDTO articleDTO = articleService.showArticle(articleId);
      int boardId = articleDTO.getBoardId();
      articleService.deleteArticle(articleId);
      return "redirect:/board/" + boardId;
   }
   
   
   /* 댓글 작성 */
   @PostMapping("/details/{articleId}")
   @ResponseBody
   public ReplyDTO replyRegist(@RequestBody ReplyDTO replyDTO, 
                        @PathVariable int articleId) {

      ReplyDTO reply = articleService.reply(replyDTO);

      return reply;
   }
   
      
}