package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class EmpController {
   // 스프링에서 객체 주소를 받을 경우 (스프링 => 클래스 관리자) => 메소드안에서는 받을 수 없다
   // 필요한 객체는 멤버로 받는다 => Service , DAO , Manager
   @Autowired  // 스프링에서 객체에 맞는 주소를 자동으로 주입 => 스프링은 싱글턴을 사용 = 재사용
   private EmpDAO eDao;
   
   // 사용자 요청별로 처리 => @GetMapping / @PostMapping / @RequestMapping
   //                                                 ================
   //                                                   스프링 6 => 제거 
   //                                       => 타일즈가 제거 
   // 1~6 교육 => 유지보수 => 1년 : 개발 ==> 3년 : 개념(면접) , 코딩(기술적인)
   // 웹 => 단순하다 (사용자 요청값 => 요청 처리(데이터베이스 연결) => 보내주는 값)
   //               매개변수         MyBatis                 addAttribute()
   // 장바구니 => id, 상품번호 , 수량
   // 펀딩    => id  펀딩번호 , 기부 
   @GetMapping("emp/list.do")
   // <form> ajax => @PostMapping => 보안 (로그인,회원가입) => 데이터를 많이 전송 
   public String emp_list(Model model)
   {
	   // 데이터베이스 연동 => Mapper(선언=자동 구현) => DAO(스프링에서 구현해준 메소드모음)
	   List<EmpVO> list=eDao.empListData();
	   model.addAttribute("list", list);
	   return "emp/list";
   }
   // emp/detail.do?empno=${vo.empno }
   @GetMapping("emp/detail.do")
   public String emp_detail(int empno,Model model)
   {
	   EmpVO vo=eDao.empDetailData(empno);
	   model.addAttribute("vo", vo);
	   return "emp/detail";
   }
   
   
}
