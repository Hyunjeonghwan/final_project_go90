package com.ezen.go90.web.member.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.go90.domain.match.dto.Participant;
import com.ezen.go90.domain.match.service.MatchService;
import com.ezen.go90.domain.member.dto.LoginForm;
import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.member.service.MemberService;
import com.ezen.go90.web.member.exception.MemberException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 관련 웹 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @Project final_project_go90
 * @Author 현정환, 주성민
 * @Date 2023. 9. 8.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

   /** 회원 관련 비즈니스 메소드 제공 */
   private final MemberService memberService;
   private final MatchService matchService;

   @Value("${common.uploadPath}")
   private String location;

   /** 사용자 회원가입 화면 요청 처리 */
   @GetMapping("/register")
   public String registerForm(Model model) {
      Member member = Member.builder().build();
      model.addAttribute("member", member);
      return "member/register";
   }

   /** 사용자 회원가입 요청 처리 회원 데이터 검증 시 Bean Validation 사용 */
   @PostMapping("/register")
   public String register(@Validated @ModelAttribute Member member, BindingResult bindingResult,
         @RequestParam(value = "memberImg", required = false) MultipartFile memberImg,
         RedirectAttributes redirectAttributes, Model model) {

      if (!memberImg.isEmpty()) {
         try {
            // 업로드 경로 설정
            String uploadDir = "C:/ezen-fullstack/workspace/final_project_go90/upload/";

            // 업로드할 파일 이름 설정 (예: 유저ID.jpg)
            String fileName = member.getMemberId() + ".jpg";

            // 업로드 경로와 파일 이름을 결합하여 파일 객체 생성
            File upload = new File(uploadDir, fileName);

            // 파일 업로드 실행
            memberImg.transferTo(upload);

            member.setMemberImg(fileName);
            log.info(" fileName:{}", fileName);
            log.info(" uploadFile : {}", memberImg);
            // 여기서 파일 업로드 성공한 후의 로직을 추가할 수 있습니다.

         } catch (IOException e) {
            e.printStackTrace();
         }
      }

//      // 데이터 검증 실패 시 회원가입 화면으로 포워드
//      if (bindingResult.hasErrors()) {
//         // model에 bindingResult 자동 저장
//         return "member/register";
//      }

      // 데이터 검증 성공 시 회원 등록 처리 후 회원 상세 페이지로 리다이렉트
      memberService.register(member);
      // 일반 회원 상세 조회 페이지와 구별하기 status 속성 추가
      redirectAttributes.addFlashAttribute("status", true);
      return "redirect:/";
   }

   /** 마이페이지 */
   @GetMapping("/mypage/{id}")
   public String read(@PathVariable("id") String id, Model model) {
      Member member = memberService.getMember(id);
      model.addAttribute("member", member);

      return "member/mypage";
   }

   @GetMapping("/profile/{imageName}")
   @ResponseBody
   public ResponseEntity<Resource> showProfile(@PathVariable String imageName) throws IOException {

      Path path = Paths.get(location + "/" + imageName);
      String contentType = Files.probeContentType(path);
      Resource resource = new FileSystemResource(path);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, contentType);
      return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
   }

   /** 회원 목록 조회 요청 처리 */
   @GetMapping
   public String list(Model model) {
      List<Member> member = memberService.getMemberList();
      model.addAttribute("member", member);
      return "member/list";
   }

   /** 회원 정보 수정 요청 처리 */
   @PostMapping("/mypage/{memberId}")
   public String edit(@PathVariable("memberId") String memberId,
                       @ModelAttribute Member member,
                       @RequestParam(value = "uploadImg", required = false) MultipartFile uploadImg) {
      
      if (!uploadImg.isEmpty()) {
         try {
            // 업로드 경로 설정
            String uploadDir = "C:/ezen-fullstack/workspace/final_project_go90/upload/";

            // 업로드할 파일 이름 설정 (예: 유저ID.jpg)
            String fileName = member.getMemberId() + ".jpg";

            // 업로드 경로와 파일 이름을 결합하여 파일 객체 생성
            File upload = new File(uploadDir, fileName);

            // 파일 업로드 실행
            uploadImg.transferTo(upload);
            
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      
      memberService.editMember(member);
      return "redirect:/member/mypage/"+member.getMemberId();

   }

   /** 회원 로그인 화면 요청 처리 */
   @GetMapping("/login")
   public String loginForm(Model model) {
      LoginForm loginForm = LoginForm.builder().build();
      model.addAttribute("loginForm", loginForm);
      return "member/login";
   }

   /** 회원 로그인 요청 처리 */
   @PostMapping("/login")
   public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
         HttpServletRequest request) {

      // 데이터 검증 실패 시 로그인 화면으로 포워드
      if (bindingResult.hasErrors()) {
         return "member/login";
      }

      // 데이터 검증 정상 처리 시
      Member loginMember = memberService.isMember(loginForm.getLoginId(), loginForm.getPasswd(), loginForm.getRank());

      // 회원이 아닌 경우
      if (loginMember == null) {
         bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
         return "member/login";
      }

      // 회원인 경우 세션 생성 및 로그인 아이디 설정
      HttpSession session = request.getSession();
      session.setAttribute("loginMember", loginMember);

      // 로그인 처리 후 사용자가 원래 요청하려던 URL로 리다이렉트 처리
      String redirectURI = (String) session.getAttribute("redirectURI");
      log.warn(redirectURI);
      String uri = redirectURI == null ? "/" : redirectURI;
      return "redirect:" + uri;
   }

   /** 회원 로그아웃 요청 처리 */
   @GetMapping("/logout")
   public String logout(HttpServletRequest request) {
      // 세션이 있으면 기존 세션 반환, 없으면 생성하지 않고 null 반환
      HttpSession session = request.getSession(false);
      if (session != null) {
         session.invalidate();
      }
      return "redirect:/";
   }

   /** 아이디 중복 여부 요청 처리 */
   @GetMapping("/valid/{id}")
   @ResponseBody
   public boolean isMemberId(@PathVariable("id") String id) {
      Member member = memberService.getMember(id);
      return member != null ? true : false;
   }
   
   /**회원 탈퇴*/
   @PostMapping("/delete/{memberId}")
   public String delete(@PathVariable("memberId") String memberId, Model model) {
      
      memberService.secession(memberId);
      
      return "redirect:/member/logout";
   }
   
   /**마이페이지 회원 기록 처리*/
   @GetMapping("/{memberId}/mystats")
   public String loadStats(Model model,@PathVariable("memberId")String memberId) {
      
      Member member = memberService.getMember(memberId);
      
      Map<String, List<Participant>> playerStats = matchService.loadAllHistory(memberId);
         model.addAttribute("member",member);
         model.addAttribute("playerStats",playerStats);
      return "member/mystats";
   }

}