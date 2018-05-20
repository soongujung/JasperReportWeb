package com.spring.scrapper.jasper.test;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.spring.scrapper.jasper.domain.BookVO;

public class JasperReportTestDAOImpl implements JasperReportTestDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<BookVO> selectBookList() {
		return sqlSession.selectList("com.spring.scrapper.jasper.selectAllFromBook");
	}
	
	@Override
	public int insertIntoBook(BookVO bookVO) {
		int result = sqlSession.insert("com.spring.scrapper.jasper.insertIntoBook", bookVO);
		return result;
	}
	
}
