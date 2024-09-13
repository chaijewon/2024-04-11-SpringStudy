package com.sist.service;

import com.sist.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
@Service
public class MemberServiceImpl implements MemberService{
    @Autowired    
 	private MemberDAO mDao;
    // 서비스의 기본 
	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		MemberVO vo=new MemberVO();
		int count=mDao.memberIdCount(id);
		if(count==0)
		{
			// ID가 없는 상태 
			vo.setMsg("NOID");
		}
		else
		{
		    MemberVO dvo=mDao.memberGetPassword(id);
		    if(pwd.equals(dvo.getPwd())) //LOGIN
		    {
		        vo.setId(dvo.getId());
		        vo.setName(dvo.getName());
		        vo.setAdmin(dvo.getAdmin());
		        vo.setMsg("OK");
		        // session에 저장 목적 
		    }
		    else // Password가 틀린 상태 
		    {
		    	vo.setMsg("NOPWD");
		    }
		}
		return vo;
	}
	
    

}
