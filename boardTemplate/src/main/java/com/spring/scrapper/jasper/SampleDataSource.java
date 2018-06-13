package com.spring.scrapper.jasper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.scrapper.jasper.domain.BookVO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class SampleDataSource implements JRDataSource{
	
	private List<BookVO> bookList = null;
	private int count = -1;
	
	private Map<String, BookVO> dataMap = new HashMap<>();
	private BookVO lastDataAdded = null;
	
	public void setBookList(List<BookVO> bookList) {
		this.bookList = bookList;
	}
	
	public static JRDataSource getDataSource() {
		return new SampleDataSource();
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		BookVO bookVO = null;
		
		if(dataMap.containsKey(jrField.getName()))
			bookVO = dataMap.get(jrField.getName());
		
		return bookVO;
	}

	@Override
	public boolean next() throws JRException {
		if(count < bookList.size()-1) {
			return true;
		}
		return false;
	}
	
}
