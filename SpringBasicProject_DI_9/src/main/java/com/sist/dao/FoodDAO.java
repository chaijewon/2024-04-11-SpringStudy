package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
// 메모리 할당 
@Repository // foodDAO
public class FoodDAO {
   @Autowired // 스프링에서 저장된 객체 주소를 찾아서 주입 => 자동 주입 
   private FoodMapper mapper;
   
   public List<FoodVO> foodFindData(Map map)
   {
	   return mapper.foodFindData(map);
   }
}
