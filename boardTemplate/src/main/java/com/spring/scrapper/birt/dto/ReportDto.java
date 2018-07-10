package com.spring.scrapper.birt.dto;

import java.util.ArrayList;
import java.util.List;

public class ReportDto {
	private String title;
	private String name;
	private List<Parameter> parameters = null;
	
	public String getTitle(){
		return title;
	}
	
	public ReportDto(){
		super();
		parameters = new ArrayList<>();
	}
	
	public ReportDto(String title, String name){
		this();
		this.title = title;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static class Parameter{
		private String title;
		private String name;
		// private ParameterType type;
		
		public Parameter(){
			super();
		}
		
		public Parameter(String title, String name){
			this();
			this.title = title;
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}


