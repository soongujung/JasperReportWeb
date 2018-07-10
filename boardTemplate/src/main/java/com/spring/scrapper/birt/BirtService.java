package com.spring.scrapper.birt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;

import com.spring.scrapper.birt.dto.ReportDto;

public interface BirtService {
	public void getBirtReport(IRenderOption option);
	public void loadReports(HttpServletRequest request) throws EngineException;
	public List<ReportDto> getReports();
}
