package com.sist.main2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
/*
 *   <bean id="dao" class="com.sist.dao.EmpDAO"
      p:mapper-ref="mapper"
    />
 */
@Repository("dao")  // empDAO (ÀÚµ¿ ID)
public class EmpDAO {
  @Autowired
  private EmpMapper mapper;
  
  public List<EmpVO> empListData()
  {
	  return mapper.empListData();
  }
}
