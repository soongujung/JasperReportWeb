package com.spring.scrapper.birt.util;

import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class BirtViewResolver extends UrlBasedViewResolver{
	
	private String reportsDirectory = "";
	private IReportEngine birtEngine;
	private DataSource dataSource;
	private int taskType;
	private Map reportParameters = null;
	
	public void setReportsDirectory(String reportsDirectory){
		this.reportsDirectory = reportsDirectory;
	}
	
	public BirtViewResolver(){
		setViewClass(AbstractSingleFormatBirtView.class);
		setSuffix(".rptdesign");
	}

	// getters, setters //
	public IReportEngine getBirtEngine() {
		return birtEngine;
	}

	public void setBirtEngine(IReportEngine birtEngine) {
		this.birtEngine = birtEngine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
