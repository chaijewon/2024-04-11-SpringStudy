package com.sist.service;

import java.util.List;

import com.sist.vo.DeptVO;
import com.sist.vo.EmpVO;
// 결합성이 낮은 프로그램 => 유지보수가 편리하게 만드는 기능 
public interface EmpDeptService {
	public List<EmpVO> empListData();
	public List<DeptVO> deptListData();
}
