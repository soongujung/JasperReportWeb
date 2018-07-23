package com.spring.scrapper.birt.util;

public class MasterActionHandler extends SimpleRequestParameterActionHandler{

	public MasterActionHandler(){
		super(null, null);
	}
	public MasterActionHandler(String reportNameKey, String formatKey) {
		super(reportNameKey, formatKey);
	}

}
