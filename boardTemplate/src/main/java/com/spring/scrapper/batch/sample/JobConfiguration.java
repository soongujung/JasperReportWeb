package com.spring.scrapper.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {
	private static final String JOB_NAME = "jobName";
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private Step stepName;
	
	@Bean
	public Job job(){
		return jobBuilderFactory.get(JOB_NAME).start(stepName).build();
	}
}
