package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sist.commons.CommonsPage;
import com.sist.service.*;
import com.sist.vo.*;
// 조립기 => Service+VO+DAO => 결과값 추출 => JSP로 전송 
@Controller  // DispatcherServlet과 연결 
/*
 *    1. 요청 받기 => DispatcherServlet 
 *    2. Model을 조회 => HandlerMapping
 *    2-1. Model 메소드 호출 => HandlerMapping 
 *    3. JSP를 찾는다 => ViewResolver
 *    3-1 JSP로 request를 전송 => ViewResolver 
 *    4. JSP에 request를 받아서 화면에 출력 
 *    5. 브라우저에서 읽기
 *    
 *    Model ======> Service =======> DAO ======> 오라클 
 *          <=======       <=======   |   <======
 *                                  유지보수
 *          결합성이 낮은 프로그램 : 스프링의 기조
 *          =================== 수정시에 다른 클래스에 영향이 없게 만든다 
 *                              =================================
 *                               | *** POJO => 독립적인 클래스
 *                                      70%  
 *     
 *    => 스프링 : Model , JSP , DAO , VO , Service 
 *                |       |   ==== Mapper(SQL)
 *                       화면을 유지 => 자바스크립트 (Ajax => Vue => React)
 *                       다른 화면으로 변경 => <a> , <form> 
 *              화면 제어 : @Controller
 *              데이터 제어 : @RestController
 *              = @GetMapping / @PostMapping 
 *              = 리턴형 : void / String 
 *              = 매개변수 : getParameter를 대체 
 */
public class BoardController {
   // 객체를 이용해서 @Autowired를 사용하면 주소값을 받으면 => 속도가 늦다 
   // 가급적이면 생성자를 이용한다 
   private BoardService bService;
   @Autowired
   public BoardController(BoardService bService)
   {
	   this.bService=bService;
   }
   
   @GetMapping("board/list.do") // 사용자가 게시판 목록을 보여달라 요청했다면 => 조건문
   // 어노테이션은 if을 추가하는 것이다 
   // 찾기 => 스프링에서 찾아서 처리 
   public String board_list(String page,Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   List<ReplyBoardVO> list=bService.boardListData(start, end);
	   int count=bService.boardRowCount();
	   int totalpage=(int)(Math.ceil(count/(double)rowSize));
	   count=count-((curpage*rowSize)-rowSize);
	   // 15/10 => 1.0
	   // 15/10.0 => 1.5 => 2
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("count", count);
	   model.addAttribute("type", "관리자가 삭제한 게시물입니다");
	   model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   model.addAttribute("main_jsp", "../board/list.jsp");
	   return "main/main";
   }
   @GetMapping("board/insert.do")
   public String board_insert(Model model)
   {
	   model.addAttribute("main_jsp", "../board/insert.jsp");
	   return "main/main";
   }
   @PostMapping("board/insert_ok.do")
   public String board_insert_ok(ReplyBoardVO vo)
   {
	   bService.boardInsert(vo);
	   return "redirect:../board/list.do";
   }
   // board/detail.do?no=${vo.no }
   /*
    *   매개변수 : 순서와 상관 없다 
    *            사용자가 요청한 값 
    *              => String / int / String[] / VO
    *            라이브러리 클래스 (내장 객체)
    *              => HttpServletRequest : Cookie 읽기 
    *              => HttpServletResponse : Cookie저장 , 다운로드 
    *              => HttpSession 
    *              => 데이터 전송 : Model 
    *              => sendRedirect로 데이터 전송 
    *                 RedirectAttributes 
    *              => 보안 : 보안 클래스 
    *   리턴형 : String / void 
    *           |         | ajax / 다운로드 / 스케쥴러 => task 
    *           request를 전송 : forward => "경로명/JSP명"
    *           기존의 화면 이동 : sendRedriect => "redirect:~.do"
    *           => request를 전송하지 않는다 
    *           ==================== 화면 변경 
    *   메소드명 : 개발자가 설정  => @GetMapping => URL주소 
    *   
    *   => detail.do?no=10 => 모든 데이터는 String으로 받을 수 있다 
    *      ===============
    *        (String no) -> Integer.parseInt(no)
    *        (int no)
    *   => 데이터가 많은 경우 
    *      VO  *******
    *      List ****** File멀티 업로드
    *      String[] = checkbox
    *      
    *   => 404 => 파일이 없는 경우 : 경로명 , 파일 여부 
    *      500 => 소스 오류 : SQL문장 
    *             NULL일때 String메소드 이용 
    *      400 => Bad Request => 매개변수의 데이터형이 다른 경우 
    *      405 => GET = @GetMapping , POST => @PostMapping
    *                                 <form> , ajax , axios.post
    *      403 => 접근 거부 : 권한 부여 => security
    *      412 => UTF-8  => 한글 변환 코드가 틀린 경우
    *             UFT-8 
    */
   @GetMapping("board/detail.do")
   public String board_detail(int no,Model model)
   {
	   
	   // 데이터베이스 연동 
	   ReplyBoardVO vo=bService.boardDetailData(no);
	   
	   // 결과값 전송
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../board/detail.jsp");
	   // request.setAttribute()
	   /*
	    *   request/response사용하면 안되는 이유 
	    *   =================
	    *   | 사용자 정보를 가지고 있다 => IP,컴퓨터에 대한 정보가 노출 가능 
	    *   | 스프링 5 => 보안 
	    *               ==== request,response사용 빈도가 거의 없다 
	    *                    Cookie => @Cookie
	    *               ==== XML을 사용하지 않는다 === 자바 설정파일이 생성 
	    */
	   return "main/main";// forward => request전송 => class화 Model
   }
   // board/update.do?no=${vo.no }
   @GetMapping("board/update.do")
   public String board_update(int no,Model model)
   {
	   ReplyBoardVO vo=bService.boardUpdateData(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../board/update.jsp");
	   return "main/main";
   }
   // board/reply.do?no=${vo.no }
   @GetMapping("board/reply.do")
   public String board_reply(int no,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("main_jsp", "../board/reply.jsp");
	   return "main/main";
   }
   @PostMapping("board/reply_ok.do")
   public String board_reply_ok(int pno,ReplyBoardVO vo)
   {
	   // 처리 
	   bService.boardReplyInsert(pno, vo);
	   return "redirect:../board/list.do";
   }
   // board/delete.do?no=${vo.no }
   @GetMapping("board/delete.do")
   public String board_delete(int no,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("main_jsp", "../board/delete.jsp");
	   return "main/main";
   }
}
