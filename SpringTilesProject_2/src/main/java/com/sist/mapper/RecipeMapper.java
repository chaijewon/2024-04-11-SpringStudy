package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface RecipeMapper {
  /*
   *   <select id="recipeListData" resultType="RecipeVO" parameterType="hashmap">
	     SELECT no,title,poster,chef,num
	     FROM (SELECT no,title,poster,chef,rownum as num 
	     FROM (SELECT no,title,poster,chef 
	     FROM recipe ORDER BY no ASC))
	     WHERE num BETWEEN #{start} AND #{end}
	   </select>
	   
	   = 조인 
   */
	public List<RecipeVO> recipeListData(Map map);
	
	@Update("UPDATE recipe SET "
		   +"hit=hit+1 "
		   +"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT * FROM recipeDetail WHERE no=#{no}")
	public RecipeDetailVO recipeDetailData(int no);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
	public int recipeTotalPage();
}
