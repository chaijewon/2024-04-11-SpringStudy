package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// empDAO

import com.sist.mapper.EmpMapper;
import com.sist.vo.EmpVO;
@Repository
public class EmpDAO {
   @Autowired
   private EmpMapper mapper;
   
   public List<EmpVO> empListData(){
	   return mapper.empListData();
   }
}
