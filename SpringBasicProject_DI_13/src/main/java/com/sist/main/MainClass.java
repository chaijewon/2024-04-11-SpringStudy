package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        	new ClassPathXmlApplicationContext("application-*.xml");
        EmpDAO dao=(EmpDAO)app.getBean("empDAO");
        List<EmpVO> list=dao.empListData();
        for(EmpVO vo:list)
        {
        	System.out.println(vo.getEmpno()+" "
        			+vo.getEname()+" "
        			+vo.getJob()+" "
        			+vo.getDbday()+" "
        			+vo.getSal()+" "
        			+vo.getDvo().getDname()+" "
        			+vo.getDvo().getLoc()+" "
        			+vo.getSvo().getGrade());
        }
	}

}
