package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.DeptMapper;
import com.sist.vo.DeptVO;
// XML , Annotation => 동시 설정
//       | 사용자 정의
// | 라이브러리 사용
@Repository
public class DeptDAO {
  @Autowired
  private DeptMapper mapper;
  
  public List<DeptVO> deptListData()
  {
	  return mapper.deptListData();
  }
}
