package com.spring.scrapper.batch.sample;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfiguration {
	private static final String STEP_NAME = "StepName";
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
}
