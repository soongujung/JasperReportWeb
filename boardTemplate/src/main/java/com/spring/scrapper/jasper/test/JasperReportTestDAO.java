package com.spring.scrapper.jasper.test;

import java.util.List;

import com.spring.scrapper.jasper.domain.BookVO;

public interface JasperReportTestDAO {
	public List<BookVO> selectBookList();
	public int insertIntoBook(BookVO bookVO);
}
