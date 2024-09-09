package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
	private BoardDAO dao;

	@Override
	public List<ReplyBoardVO> boardListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.boardListData(start, end);
	}

	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return dao.boardRowCount();
	}

	@Override
	public void boardInsert(ReplyBoardVO vo) {
		// TODO Auto-generated method stub
		dao.boardInsert(vo);
	}

	@Override
	public ReplyBoardVO boardDetailData(int no) {
		// TODO Auto-generated method stub
		return dao.boardDetailData(no);
	}

	@Override
	public ReplyBoardVO boardUpdateData(int no) {
		// TODO Auto-generated method stub
		return dao.boardUpdateData(no);
	}

	@Override
	public String boardUpdate(ReplyBoardVO vo) {
		// TODO Auto-generated method stub
		return dao.boardUpdate(vo);
	}

	@Override
	public void boardReplyInsert(int pno, ReplyBoardVO vo) {
		// TODO Auto-generated method stub
		dao.boardReplyInsert(pno, vo);
	}

	@Override
	public String boardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		return dao.boardDelete(no, pwd);
	}
    
    
}
