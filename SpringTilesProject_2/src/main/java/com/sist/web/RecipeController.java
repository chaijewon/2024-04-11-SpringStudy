package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeDetailVO;
import java.util.*;
@Controller
public class RecipeController {
   @Autowired
   private RecipeDAO rDao;
   
   @GetMapping("recipe/detail.do")
   // recipe/detail.do?no=548 ==> 매개변수 => DispatcherServlet
   /*
    *   1. int 
    *   2. String 
    *   3. String[]
    *   4. ~VO
    *   ==============
    *   1. Model : 전송할 데이터가 있는 경우 
    *   2. HttpSession 
    *   3. HttpServletRequest , HttpServletResponse 
    *      => Cookie / 업로드 
    *   4. RedirectAttributes : sendRedirect시에 데이터 전송 
    */
   public String recipe_detail(int no,Model model)
   {
	   // String no=request.getParameter("no") => Integer.parseInt(no)
	   // DB연동 
	   RecipeDetailVO vo=rDao.recipeDetailData(no);
	   String data=vo.getData();
	   data=data.replace("구매", "");
	   vo.setData(data.trim());
	   
	   List<String> mList=new ArrayList<String>();
	   List<String> iList=new ArrayList<String>();
	   
	   String[] foodMake=vo.getFoodmake().split("\n");
	   /*
	    *    1. ..... ^ 이미지\n
	    *    2
	    *    3
	    *    4....
	    */
	   for(String fm:foodMake)
	   {
		   StringTokenizer st=new StringTokenizer(fm,"^");
		   mList.add(st.nextToken());
		   iList.add(st.nextToken());
	   }
	   
	   
	   
	   // JSP => 출력할 데이터 전송 
	   model.addAttribute("vo", vo);
	   model.addAttribute("mList", mList);
	   model.addAttribute("iList", iList);
	   return "recipe/detail";
   }
}
