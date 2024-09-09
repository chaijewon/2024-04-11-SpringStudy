package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;
// Controller => JSP관리 => JSP파일명 , .do
// RestController => 자바스크립트 연동 , 코틀린 연동 => 다른 언어 연동
//  => 일반 문자열 , JSON 
@RestController  // @ResponseBody (사라짐) => @RestController 사용이 많이 됨

public class BoardRestController {
    @Autowired
	private BoardService bService;
    @PostMapping("board/update_ok.do")
    public String board_update_ok(ReplyBoardVO vo)
    {
    	String result="no";
    	result=bService.boardUpdate(vo);
    	return result;
    }
}
