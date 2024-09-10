package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class RecipeDAO {
    @Autowired
    private RecipeMapper mapper;
    
    public List<RecipeVO> recipeListData(Map map)
    {
    	return mapper.recipeListData(map);
    }
    
    public int recipeTotalPage()
    {
    	return mapper.recipeTotalPage();
    }
    /*
     *    @Update("UPDATE recipe SET "
		   +"hit=hit+1 "
		   +"WHERE no=#{no}")
	      public void hitIncrement(int no);
     *    @Select("SELECT * FROM recipeDetail WHERE no=#{no}")
	      public RecipeDetailVO recipeDetailData(int no);
     */
    public RecipeDetailVO recipeDetailData(int no)
    {
    	mapper.hitIncrement(no);
    	return mapper.recipeDetailData(no);
    }
}
