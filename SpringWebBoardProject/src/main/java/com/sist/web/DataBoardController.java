package com.sist.web;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/*
 *   =========================== 화면 
 *   @Controller : 화면 지정 => 어떤 화면을 보여줄지 선택 
 *                 ======= 출력할 데이터 전송 : Model 
 *                         addAttribute()
 *   @RestController : 자바스크립트 전송 , JSON => Ajax , Vue , React 
 *                     => 화면과 관련이 없다
 *                     
 *   사용하는 형식 => 스프링에서 지정 
 *   ========== 메소드 관리 
 *              메소드와 관련 
 *              = 리턴형 
 *                 String : forward , redirect 
 *                            |          |
 *                          데이터 전송   이전에 등록된 화면 이동
 *                                      return "redirect:list.do"
 *                                      => _ok.do 
 *                          return "경로명/파일명"
 *                 void : 다운로드 
 *              = 매개변수 : => DispatcherServlet에서 매개변수 설정 
 *                        getParameter() => 매개변수로 처리 
 *                        => ~VO단위로 값을 받는다 => insert/update/join...
 *                           ================ 커맨드 객체 
 *                        => 모든 데이터 : String => 해당 데이터형 
 *                           ==================
 *                           1. 값이 없는 경우 : String => page를 받는 경우 
 *                                                      검색어
 *                                            ======================= 디폴트 설정
 *                        => String[] : checkbox를 이용해서 값을 받는 경우 
 *                        => 내장 객체 
 *                           HttpServletRequest => 사용금지를 권장
 *                              => 단점은 사용자 IP,PORT확인이 가능 
 *                              => 사용시 : Cookie값 읽기  
 *                           HttpServletResponse => 사용금지를 권장
 *                              => 사용시 : Cookie 저장 => 브라우저로 전송 
 *                              => 다운로드 
 *                           HttpSession => Login / session이 필요한 위치 
 *                           RedirectAttributes => redirect로 값을 전송 
 *                            return "redirect:detail.do?no="+no
 *                               ra.addAttribute("no",no)
 *                            => return "redirect:detail.do";
 *                           Model : request를 대체하는 전송 객체 
 *                           => Cookie는 내장객체가 아니다 (request를 이용한다)
 *                     => 매개변수는 순서없이 설정이 가능 
 *                           
 *              = 메소드명 : 자유롭게.. => URL주소만 매칭
 *   =========================== 데이터베이스 연동   
 *   @Repository : 테이블 한개만 연결 => 재사용 
 *   @Service : DAO가 여러개 있는 경우 
 *              emp / dept 
 *              board / reply 
 *   ===========================  오류 처리 
 *   @ControllerAdvice : 통합 예외처리 => WEB에서만 가능 
 *   
 *   => 스프링에서 주로 사용되는 패키지 
 *      com.sist.vo : 사용자 정의 데이터형 => 데이터베이스 컬럼명 => 오라클로부터 데이터 저장
 *      com.sist.dao : 데이터베이스 연동 
 *      com.sist.service : DAO를 여러개 묶어서 사용
 *      com.sist.manager : 추천 , 크롤링 , 외부데이터 읽기 => OpenAPI
 *                         날씨 , 실시간 검색어 ....(Naver,Kakao)
 *      com.sist.web : Model => 조립 => 결과값 전송 
 *      com.sist.intercept : 중간에 처리가 가능 
 *                                                  | (intercept) => 자동 로그인
 *        사용자 요청 (.do) ===== DispatcherServlet ====== HandlerMapping 
 *                                                            |
 *                                                       com.sist.web
 *                                                       @GetMapping
 *                                                       @PostMapping
 *                                                       @RequestMapping 
 *                                                       => URL주소를 이용해서 메소드를 호출 
 *                                                     (intercept)- | 결과값 (Model) 
 *                                                       ViewResolver
 *                                                  (intercept)    - | request로 변환 
 *                                                           JSP
 *                                                            
 *      com.sist.aop : 공통으로 사용 => 요청 처리 수행 시간 
 *                     => footer에 출력 : 자동화 
 *      com.sist.commons : 예외처리를 한번에 처리 
 *      com.sist.security : login (접근거부) , 권한부여 => 메뉴 변경 
 *                          remember-me : 자동 로그인 
 *                          비밀번호 : 암호화 
 *      com.sist.chat : webchat : 실시간 채팅 
 *                        | 접속자 전체 채팅 
 *                        | 상담사와 채팅 => 1:1채팅 
 *      ================================================
 *      패키지안에서 사용하는 클래스 => 관리하는 영역(스프링) 
 *                               === 클래스 메모리 할당 ===== 소멸 (메모리 해제)
 *      * DispatcherServlet 
 *        =================
 *        1. @WebServlet("*.do") => 스프링에는 존재하지 않는다 
 *           => web.xml에 등록 
 *              ======= 톰캣에서 읽어가는 파일 
 *                => 서블릿과 JSP는 톰캣에 의해 구동 
 *                   ========== 찾는 방법 : URL
 *                => request를 사용하지 않는다 
 *                   => 한글 변환 => 등록 
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.DataBoardVO;
import java.net.*;
@Controller
@RequestMapping("databoard/") // 공통으로 사용되는 URL주소를 모아서 => 적용
public class DataBoardController {
   @Autowired
   private BCryptPasswordEncoder encoder;
   @Autowired
   private DataBoardDAO dao;
   
   // 사용자 요청에 대한 처리 
   @GetMapping("list.do")
   public String databoard_list(String page,Model model)
   {
	   //String en=encoder.encode("1234");
	   //System.out.println(en);
	   
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   List<DataBoardVO> list=dao.databoardListData(start, end);
	   // 총페이지 
	   int count=dao.databoardRowCount();
	   int totalpage=(int)(Math.ceil(count/(double)rowSize));
	   count=count-((rowSize*curpage)-rowSize);
	   // 사용자로부터 받는 값 => Model : 결과값 전송 객체 
	   // 출력에 필요한 데이터를 list.jsp로 전송 
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("count", count);
	   model.addAttribute("list", list);
	   
	   return "databoard/list";
   }
   // 데이터 추가 
   @GetMapping("insert.do")
   public String databoard_insert()
   {
	   return "databoard/insert";
   }
   // 실제 데이터 첨부 
   @PostMapping("insert_ok.do")
   public String databoard_insert_ok(DataBoardVO vo)
   {
	   String en=encoder.encode(vo.getPwd());// 암호화 
	   vo.setPwd(en);
	   String path="c:\\spring_upload";
	   List<MultipartFile> list=vo.getFiles();
	   if(list==null)
	   {
		   vo.setFilename("");
		   vo.setFilesize("");
		   vo.setFilecount(0);
	   }
	   else // 업로드가 있는 경우
	   {
		  try
		  {
			   String filename="";
			   String filesize="";
			   for(MultipartFile mf:list)
			   {
				   String name=mf.getOriginalFilename(); // 사용자가 보내준 파일명
				   File file=new File(path+"\\"+name);
				   mf.transferTo(file);// 파일 업로드 
				   filename+=file.getName()+",";
				   filesize+=file.length()+",";
			   }
			   filename=filename.substring(0,filename.lastIndexOf(","));
			   filesize=filesize.substring(0,filesize.lastIndexOf(","));
			   
			   vo.setFilename(filename);
			   vo.setFilesize(filesize);
			   vo.setFilecount(list.size());
		  }catch(Exception ex){}
	   }
	   dao.databoardInsert(vo);
	   return "redirect:list.do";
   }
   // 상세보기 
   @GetMapping("detail.do")
   public String databoard_detail(int no,Model model)
   {
	   DataBoardVO vo=dao.databoardDetailData(no);
	   if(vo.getFilecount()!=0)
	   {
		   List<String> nList=new ArrayList<String>();
		   List<String> cList=new ArrayList<String>();
		   String[] names=vo.getFilename().split(",");
		   String[] lens=vo.getFilesize().split(",");
		   nList=Arrays.asList(names);
		   cList=Arrays.asList(lens);
		   // 배열 => List로 변경 
		   
		   model.addAttribute("nList", nList);
		   model.addAttribute("cList", cList); // a.jpg(1000byte)
	   }
	   model.addAttribute("vo", vo);
	   return "databoard/detail";
   }
   // 다운로드 MVC
   @GetMapping("download.do")
   public void databoard_download(String fn,HttpServletResponse response)
   {
	   // => VueJS
	   try
	   {
		   String path="c:\\spring_upload";
		   // 서버에서 파일을 읽어서 한개의 메모리에 저장 => 속도를 빠르게 
		   BufferedInputStream bis=
				   new BufferedInputStream(new FileInputStream(path+"\\"+fn));
		   // 다운로드 요청한 클라이언트 
		   BufferedOutputStream bos=
				   new BufferedOutputStream(response.getOutputStream());
		   
		   // 1.다운로드창을 보여준다 
		   response.setHeader("Content-Disposition", "attachment;filename="
				     +URLEncoder.encode(fn,"UTF-8"));
		   File file=new File(path+"\\"+fn);
		   response.setContentLength((int)file.length());// 프로그래스바
		   
		   // 실제 다운로드 
		   byte[] buffer=new byte[1024]; // TCP:1024 , UDP : 512
		   int i=0; // 파일에서 읽은 파이트 크기 
		   while((i=bis.read(buffer, 0, 1024))!=-1)
		   {
			   // -1 : EOF
			   bos.write(buffer, 0, i);
		   }
		   bis.close();
		   bos.close();
	   }catch(Exception ex){}
   }
   
   @GetMapping("delete.do")
   public String databoard_delete(int no,Model model)
   {
	   model.addAttribute("no", no);
	   return "databoard/delete";
   }
   
   @PostMapping("delete_ok.do")
   public String databoard_delete_ok(int no,String pwd,Model model)
   {
	   DataBoardVO vo=dao.databoardFileInfoData(no);
	   boolean bCheck=dao.databoardDelete(no, pwd);
	   model.addAttribute("bCheck", bCheck);   
	   try
	   {
		   String files=vo.getFilename();
		   if(vo.getFilecount()!=0) // 파일이 있는 경우 
		   {
			   StringTokenizer st=new StringTokenizer(files,",");
			   while(st.hasMoreTokens())
			   {
				   File file=new File("c:\\spring_upload\\"+st.nextToken());
				   file.delete();
			   }
		   }
	   }catch(Exception ex) {}
	   return "databoard/delete_ok";
   }
}





