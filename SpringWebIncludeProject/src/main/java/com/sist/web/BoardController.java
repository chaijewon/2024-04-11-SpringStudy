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
   @GetMapping("board/detail.do")
   public String board_detail(int no,Model model)
   {
	   // 데이터베이스 연동 
	   // 결과값 전송
	   model.addAttribute("main_jsp", "../board/detail.jsp");
	   return "main/main";
   }
}
