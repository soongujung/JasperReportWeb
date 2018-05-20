package com.spring.scrapper.mybatis;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MybatisSessionTest {
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testFactory(){
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void testSession() throws Exception{
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println("세션 획득 성공 ");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
