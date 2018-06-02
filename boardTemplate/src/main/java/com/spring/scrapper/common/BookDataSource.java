package com.spring.scrapper.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class BookDataSource implements JRDataSource{

	private static final String [] bookArray = {"유럽축구의 역사", "K리그 6월 동향 ", "풋볼 매거진"};
	private static final Integer[] priceArray = {10000, 20000, 3000};
	private int count = -1;
	private int lastItemAddedIdx = 0;
	
	private HashMap<String, Integer> productMap = new HashMap<String, Integer>();
	
	@Override
	public boolean next() throws JRException {
		if(count < bookArray.length-1){
			count++;
			return true;
		}
		return false;
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Integer index;
		if(productMap.containsKey(jrField.getName()))
			index = productMap.get(jrField.getName());
		else{
			productMap.put(jrField.getName(), lastItemAddedIdx);
			index = lastItemAddedIdx++;
		}
		
		if(index == 0) return bookArray[count];
		else if(index ==1) return priceArray[count];
		
		return "";
	}

	
	public static JRDataSource getSampleDataSource(){
		return new BookDataSource();
	}
	
	public static Map<String, Object> getBookDataSource(){
		List<String> bookList = Arrays.asList(bookArray);
		List<Integer> priceList = Arrays.asList(priceArray);
		
		Iterator<String> bookIterator = bookList.iterator();
		Iterator<Integer> priceIterator = priceList.iterator();
		
		Map<String, Object> resultMap = new HashMap<>();
		
		while(bookIterator.hasNext()){
			String book = (String) bookIterator.next();
			Integer price = (Integer) priceIterator.next();
			resultMap.put(book, price);
		}
		
		return resultMap;
	}
}
