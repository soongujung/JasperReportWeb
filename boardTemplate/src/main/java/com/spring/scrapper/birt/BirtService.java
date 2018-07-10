package com.spring.scrapper.birt;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;

public interface BirtService {
	public void getBirtReport(IRenderOption option);
	public void loadReports(HttpServletRequest request) throws EngineException;
}
