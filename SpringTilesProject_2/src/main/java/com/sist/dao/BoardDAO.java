package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
// 오라클 연동 
@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper;
   
   /*
    *   @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		  +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		  +"FROM (SELECT no,subject,name,regdate,hit "
		  +"FROM spring_board ORDER BY no DESC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
        public List<BoardVO> boardListData(Map map);
    */
   public List<BoardVO> boardListData(Map map)
   {
	   return mapper.boardListData(map);
   }
   /*
    *   @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
        public int boardTotalPage();
    */
   public int boardTotalPage()
   {
	   return mapper.boardTotalPage();
   }
}
