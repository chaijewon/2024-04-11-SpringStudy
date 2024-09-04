package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class DataBoardDAO {
   @Autowired
   private DataBoardMapper mapper;
   
   /*
    * @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			 +"FROM (SELECT no,subject,name,regdate,hit "
			 +"FROM spring_board ORDER BY no DESC)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")
	  public List<DataBoardVO> databoardListData(@Param("start") int start,@Param("end") int end);
	  
	  @Select("SELECT COUNT(*) FROM spring_databoard")
	  public int databoardRowCount();
	  
	  
	  @Insert("INSERT INTO spring_databoard(no,name,subject,content,pwd,"
			 +"filename,filesize,filecount) VALUES("
			 +"sd_no_seq.nextval,#{name},#{subject},#{content},"
			 +"#{pwd},#{filename},#{filesize},#{filecount})")
	  public void databoardInsert(DataBoardVO vo);
    */
   public List<DataBoardVO> databoardListData(int start,int end)
   {
	   return mapper.databoardListData(start, end);
   }
   public int databoardRowCount()
   {
	   return mapper.databoardRowCount();
   }
   public void databoardInsert(DataBoardVO vo)
   {
	   mapper.databoardInsert(vo);
   }
   /*
    *    @Update("UPDATE spring_databoard SET "
			 +"hit=hit+1 "
			 +"WHERE no=#{no}")
		  public void hitIncrement(int no);
		  
		  @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,"
				 +"hit,filename,filesize,filecount "
				 +"FROM spring_databoard "
				 +"WHERE no=#{no}")
		  public DataBoardVO databoardDetailData(int no);
    */
   public DataBoardVO databoardDetailData(int no)
   {
	   mapper.hitIncrement(no);
	   return mapper.databoardDetailData(no);
   }
}
