package com.spring.scrapper.board;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Inject
	private SqlSession sqlSession;
	
	@RequestMapping(value="/list",  method=RequestMethod.GET)
	public void listGet(){
		
	}
	
}
