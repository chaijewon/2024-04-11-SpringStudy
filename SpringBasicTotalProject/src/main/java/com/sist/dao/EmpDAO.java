package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class EmpDAO {
   @Autowired  // 객체의 주소값만 주입이 가능(일반 변수는 사용이 불가능)
   private EmpMapper mapper; // Spring => MyBatis에서 구현=> 구현한 클래스의 주소를 얻어온다
	/*
	 * @Value("홍길동") private String name="";
	 */
   /*
    *      @Select("SELECT empno,ename,job,TO_CAHR(hiredate,'YYYY-MM-DD DY')as dbday,"
		    +"sal,deptno FROM emp")
		   public List<EmpVO> empListData();
		   
		   @Select("SELECT empno,ename,job,TO_CAHR(hiredate,'YYYY-MM-DD DY')as dbday,"
		   		+ "sal,deptno FROM emp WHERE empno=#{empno}")
		   public EmpVO empDetailData();
		   
		   @Select("SELECT DISTINCT ename FROM emp")
		   public List<String> empEnameList();
    */
   public List<EmpVO> empListData()
   {
	   return mapper.empListData();
   }
   public EmpVO empDetailData()
   {
	   return mapper.empDetailData();
   }
   public List<String> empEnameList()
   {
	   return mapper.empEnameList();
   }
   
   /*
    *   @Select({"<script>"
	      +"SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD DY')as dbday,"
	      +"sal,deptno FROM emp "
	      +"<trim prefix=\"WHERE ename IN(\" suffix=\")\" suffixOverrides=\")\" "
	      +"prefixOverrides=\"(\">"
	      +"<foreach collection=\"names\" item=\"name\" open=\"(\" close=\")\" separator=\",\">"
	      +"#{name}"
	      +"</foreach>"
	      +"</trim>"
	      +"</script>"})
   public List<EmpVO> empNameFindData(Map map);
    */
   public List<EmpVO> empNameFindData(Map map)
   {
	   return mapper.empNameFindData(map);
   }
   /*
    *   @Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
		  +"sal,deptno "
		  +"FROM emp "
		  +"WHERE ${fd} LIKE '%'||#{ss}||'%'")
		   // fd = ename , ss= king
		   // ${fd} => ename #{ss} => 'king' => ${} => table_name , column_name
		   public List<EmpVO> empFindData(Map map);
    */
   public List<EmpVO> empFindData(Map map)
   {
	   return mapper.empFindData(map);
   }
}
