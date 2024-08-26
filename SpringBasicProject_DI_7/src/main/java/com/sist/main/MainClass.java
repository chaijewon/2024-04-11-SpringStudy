package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;
/*
 *   1. xml => 메모리 할당 <bean>:클래스 1개 <context:component-scan>:package단위
 *                                      ========================
 *   2. 메모리 할당하는 어노테이션 
 *                  @Component
 *                  @Repository
 *                  @Service
 *                  @Controller 
 *                  @RestController
 *   3. 주소값 주입 
 *      @Autowired 
 *      @Resource
 *      @Qualifier
 *      
 *   4. AOP 
 *       @Before
 *       @After
 *       @Around
 *       @After-Returning
 *       @After-Throwing 
 *   5. MVC 
 *   6. Security 
 *      
 */
@Component("mc")
public class MainClass {
	@Autowired
	
    private EmpDAO eDao;
	
	@Autowired
    private DeptDAO dDao;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext app=
        		new ClassPathXmlApplicationContext("application-*.xml");
        MainClass mc=(MainClass)app.getBean("mc");
        List<EmpVO> eList=mc.eDao.empListData();
        List<DeptVO> dList=mc.dDao.deptListData();
        
        for(EmpVO vo:eList)
        {
        	System.out.println(vo.getEname()+" "
        			+vo.getJob()+" "
        			+vo.getDbday());
        	
        }
        System.out.println("======================");
        for(DeptVO vo:dList)
        {
        	System.out.println(vo.getDeptno()+" "
        			+vo.getDname()+" "
        			+vo.getLoc());
        }
	}

}
