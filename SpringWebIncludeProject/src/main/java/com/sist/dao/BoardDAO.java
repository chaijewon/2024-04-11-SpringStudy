package com.sist.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper;
   
   /*
    *   @Select("SELECT no,subject,name,"
		 +"TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
		 +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
		 +"FROM spring_replyboard ORDER BY no DESC)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
		  public List<ReplyBoardVO> boardListData(@Param("start") int start,
				    @Param("end") int end);
		  @Insert("INSERT INTO spring_replyboard(no,name,subject,content,pwd,group_id) "
				 +"VALUES(srb_no_seq.nextval,#{name},#{subject},"
				 +"#{content},#{pwd},"
				 +"(SELECT NVL(MAX(group_id)+1,1) FROM spring_replyboard))")
		  public void boardInsert(ReplyBoardVO vo);
		  
		  @Select("SELECT COUNT(*) FROM spring_replyboard")
          public int boardRowCount();
    */
   public List<ReplyBoardVO> boardListData(int start,int end)
   {
	   return mapper.boardListData(start, end);
   }
   public int boardRowCount()
   {
	   return mapper.boardRowCount();
   }
   public void boardInsert(ReplyBoardVO vo)
   {
	   mapper.boardInsert(vo);
   }
   /*
    *   @Update("UPDATE spring_replyboard SET "
		 +"hit = hit+1 "
		 +"WHERE no=#{no}")
		  public void hitIncrement(int no);
		  
		  @Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
				 +"FROM spring_replyboard "
				 +"WHERE no=#{no}")
		  public ReplyBoardVO boardDetailData(int no);
    */
   public ReplyBoardVO boardDetailData(int no)
   {
	   mapper.hitIncrement(no);
	   return mapper.boardDetailData(no);
   }
}
