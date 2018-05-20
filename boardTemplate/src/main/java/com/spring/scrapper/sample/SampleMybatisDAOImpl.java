package com.spring.scrapper.sample;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.scrapper.domain.sample.SampleMybatisVO;

@Repository
public class SampleMybatisDAOImpl implements SampleMybatisDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.spring.scrapper.sample";

	@Override
	public List<SampleMybatisVO> selectAllData() {
		return sqlSession.selectList(namespace + ".selectAllData");
	}
	
}
