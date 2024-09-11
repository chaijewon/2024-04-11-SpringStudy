package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface EmpMapper {
   /*
    *    <select id="empListData" resultMap="empMap">
		    SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,
		           dname,loc,grade
		    FROM emp,dept,salgrade
		    WHERE emp.deptno=dept.deptno
		    AND sal BETWEEN losal AND hisal
		  </select>
    */
	public List<EmpVO> empListData();
	//@ResultMap("empMap") => XML (id)
	/*@Results({
		@Result(property = "dvo.dname",column = "dname"),
		@Result(property = "dvo.loc",column = "loc"),
		@Result(property = "svo.grade",column = "grade")
	})*/
	@ResultMap("empMap") //XML에 등록된 <resultMap> => 매개변수:id명
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
		   +"sal,dname,loc,grade "
		   +"FROM emp,dept,salgrade "
		   +"WHERE emp.deptno=dept.deptno "
		   +"AND sal BETWEEN losal AND hisal "
		   +"AND empno=#{empno}")
	public EmpVO empDetailData(int empno);
	
	// @Select(조인)...
}






