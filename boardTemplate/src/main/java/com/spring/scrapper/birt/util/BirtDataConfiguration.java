package com.spring.scrapper.birt.util;

import java.sql.Driver;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class BirtDataConfiguration {
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(this.dataSource());
	}
	
	@Bean
	@SuppressWarnings("unchecked")
	public DataSource dataSource() throws Exception {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName("com.mysql.jdbc.Driver");
		String user = "netcruz";
		String password = "netcruz!#$134";
		String url = "jdbc:mysql://10.130.1.205:3306/NMS_DB?serverTimezone=UTC";
		
		dataSource.setDriverClass(driver);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		return dataSource;
	}
}

