package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
   @Autowired
   private FoodMapper mapper;
   
   /*
    *   @Select("SELECT fno,poster,name,num "
		  +"FROM (SELECT fno,poster,name,rownum as num "
		  +"FROM (SELECT fno,poster,name "
		  +"FROM project_food_house ORDER BY fno ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
    */
   public List<FoodVO> foodListData(int start,int end)
   {
	   return mapper.foodListData(start, end);
   }
   /*
    *   @Select("SELECT CEIL(COUNT(*)/20.0) FROM project_food_house")
        public int foodTotalPage();
    */
   public int foodTotalPage()
   {
	   return mapper.foodTotalPage();
   }
}
