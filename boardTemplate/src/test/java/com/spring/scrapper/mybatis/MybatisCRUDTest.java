package com.spring.scrapper.mybatis;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.scrapper.domain.sample.SampleMybatisVO;
import com.spring.scrapper.sample.SampleMybatisDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MybatisCRUDTest {
	
	@Inject
	private SampleMybatisDAO sampleDAO;
	
	@Test
	public void testSelectList() throws Exception{
		List<SampleMybatisVO> sampleList = sampleDAO.selectAllData();
		System.out.println("COUNT :: " + sampleList.size());
	}
}
