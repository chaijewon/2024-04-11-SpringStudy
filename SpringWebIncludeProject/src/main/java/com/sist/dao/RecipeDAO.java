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
    
    /*
     *   @Select("SELECT no,poster,title,chef,num "
			  +"FROM (SELECT no,poster,title,chef,rownum as num "
			  +"FROM (SELECT  INDEX_ASC(recipe recipe_no_pk) no,poster,title,chef "
			  +"FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail))) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
		   public List<RecipeVO> recipeListData(Map map);
		   
		   @Select("SELECT COUNT(*) FROM recipe "
				  +"WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
		   public int recipeRowCount();
     */
    public List<RecipeVO> recipeListData(Map map)
    {
    	return mapper.recipeListData(map);
    }
    public int recipeRowCount()
    {
    	return mapper.recipeRowCount();
    }
    /*
     *     @Update("UPDATE recipe SET "
			  +"hit=hit+1 "
			  +"WHERE no=#{no}")
		   public void recipeHitIncrement(int no);
		   
		   @Select("SELECT * FROM recipedetail "
				  +"WHERE no=#{no}")
		   public RecipeDetailVO recipeDetailData(int no);
     */
    public RecipeDetailVO recipeDetailData(int no)
    {
    	mapper.recipeHitIncrement(no);
    	return mapper.recipeDetailData(no);
    }
    
    // 쉐프 관련
    /*
     *   @Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
			  +"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num "
			  +"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2 "
			  +"FROM chef)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
	   public List<ChefVO> chefListData(Map map);
	   
	   @Select("SELECT CEIL(COUNT(*)/50.0) FROM chef")
	   public int chefTotalPage();
     */
    public List<ChefVO> chefListData(Map map)
    {
    	return mapper.chefListData(map);
    }
    public int chefTotalPage()
    {
    	return mapper.chefTotalPage();
    }
    
    /*
     *    @Select("SELECT no,title,poster,chef,num "
			  +"FROM (SELECT no,title,poster,chef,rownum as num "
			  +"FROM (SELECT no,title,poster,chef "
			  +"FROM recipe WHERE chef=#{chef} ORDER BY no ASC)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
		   public List<RecipeVO> chefMakeRecipeData(Map map);
		   
		   @Select("SELECT CEIL(COUNT(*)/12.0) "
				  +"FROM recipe "
				  +"WHERE chef=#{chef}")
		   public int chefMakeRecipeTotalPage(String chef);
     */
    public List<RecipeVO> chefMakeRecipeData(Map map)
    {
    	return mapper.chefMakeRecipeData(map);
    }
    public int chefMakeRecipeTotalPage(String chef)
    {
    	return mapper.chefMakeRecipeTotalPage(chef);
    }
    
    /*
     *     쿠키에 출력할 데이터
     *     @Select("SELECT no,title,poster "
			  +"FROM recipe "
			  +"WHERE no=#{no}")
	       public RecipeVO recipeCookieInfoData(int no);
     */
    public RecipeVO recipeCookieInfoData(int no)
    {
    	return mapper.recipeCookieInfoData(no);
    }
    
    /*
     *   @Select("SELECT no,title,poster,chef,num "
			  +"FROM (SELECT no,title,poster,chef,rownum as num "
			  +"FROM (SELECT no,title,poster,chef "
			  +"FROM recipe WHERE title LIKE '%'||#{fd}||'%' "
			  +"AND no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail) "
			  +"ORDER BY no ASC)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
	   public List<RecipeVO> recipeFindData(Map map);
	   @Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			  +"WHERE title LIKE '%'||#{fd}||'%' "
			  +"AND no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)")
	   public int recipeFindTotalPage(Map map);
     */
    public List<RecipeVO> recipeFindData(Map map)
    {
    	return mapper.recipeFindData(map);
    }
    public int recipeFindTotalPage(Map map)
    {
    	return mapper.recipeFindTotalPage(map);
    }
    /*
     *   // 1. 인기 맛집 
	   @Select("SELECT fno,name,rownum "
			  +"FROM (SELECT fno,name FROM project_food_house ORDER BY hit DESC) "
			  +"WHERE rownum<=5")
	   public List<FoodVO> foodTop5Data();
	   // 2. 인기 레시피 
	   @Select("SELECT no,title,rownum "
			  +"FROM (SELECT no,title FROM recipe ORDER BY hit DESC) "
			  +"WHERE rownum<=5")
	   public List<RecipeVO> recipeTop5Data();
     */
    public List<FoodVO> foodTop5Data()
    {
    	return mapper.foodTop5Data();
    }
    public List<RecipeVO> recipeTop5Data()
    {
    	return mapper.recipeTop5Data();
    }
}
