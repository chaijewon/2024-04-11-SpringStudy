package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class MainController {
    @Autowired
    private EmpDAO eDao;
    
    
    @GetMapping("main/main.do")
    public String main_main(Model model)
    {
    	//1. 데이터베이스 연동 
    	List<EmpVO> list=eDao.empListData();
    	//2. 결과값을 읽어서 => JSP로 전송 
    	model.addAttribute("list", list);
    	return "main/main";
    }
    // 동적쿼리 => foreach => in 연산자 수행 => IN('','',''....)
    @GetMapping("main/name_find.do")
    public String main_find(Model model)
    {
    	List<String> names=eDao.empEnameList();
    	model.addAttribute("names", names);
    	return "main/name_find";
    }
    
    @PostMapping("main/name_find_ok.do")
    public String main_find_ok(String[] names,Model model)
    {
    	// 데이터베이스 연동
    	Map map=new HashMap();
    	map.put("names", names);
    	List<EmpVO> list=eDao.empNameFindData(map);
    	// 데이터 전송 
    	model.addAttribute("list", list);
    	return "main/name_find_ok";
    }
    
    @RequestMapping("main/find.do")
    public String emp_find(String fd,String ss,Model model)
    {
    	if(fd==null)
    		fd="all";
    	// 데이터베이스 연동 
    	Map map=new HashMap();
    	map.put("fd", fd);
    	map.put("ss", ss);
    
    	List<EmpVO> list=eDao.empFindData(map);
    	// 데이터 전송 
    	model.addAttribute("list", list);
    	return "main/find";
    }
}
