package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.DeptVO;
public interface DeptMapper {
   @Select("SELECT deptno,dname,loc "
		  +"FROM dept ORDER BY deptno ASC")
   public List<DeptVO> deptListData();
   // 자동으로 구현이 된다 
   /*
    *     리턴형 : resultType
    *     매개변수 : parameterType
    *     메소드명 : id 
    *     <select id="deptListData" resultType="DeptVO" parameterType="">
    *      SELECT deptno,dname,loc
    *      FROM dept ORDER BY deptno ASC
    *     </select>
    */
}
