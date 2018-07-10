package com.spring.scrapper.birt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.scrapper.birt.dto.BirtBookInputFormDto;
import com.spring.scrapper.birt.dto.ReportDto;
import com.spring.scrapper.birt.vo.BirtBookInputFormVO;
import com.spring.scrapper.jasper.domain.JasperBookInputFormVO;
import com.spring.scrapper.jasper.domain.dto.JasperBookInputFormDTO;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/birt")
public class BirtController {
	@Inject
	private BirtService birtService;
	
//	@Autowired
//	private IReportEngine birtEngine;
//	@Autowired
//	private IReportEngine reportEngine;
	
	private Map renderOptions;
	
	@ModelAttribute("birtFormatMap")
	public Map<String, String> getBirtBookFormats(){
		Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("PDF", "pdf");
		parameterMap.put("HTML", "html");
		parameterMap.put("XLS", "xls");
		return parameterMap;
	}
	
	@RequestMapping(value="/report/sample", method=RequestMethod.GET)
	public String getSample(){
		return "birt/report";
	}
	
	@RequestMapping(value="/report/list", method=RequestMethod.GET)
	public List<ReportDto> getReportList(){
		return birtService.getReports();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getMenuList(){
		return "birt/list";
	}
	
	@RequestMapping(value="/iframe_menu", method=RequestMethod.GET)
	public String getTypeIframe(BirtBookInputFormVO vo){
		return "birt/iframe_menu";
	}
	
	@RequestMapping(value="/viewReport", method=RequestMethod.GET)
	public String getReportPage(){
		return "birt/iframe_birt";
	}
	
	@RequestMapping(value="/viewReport", method=RequestMethod.POST)
	public String getReportPagePost(BirtBookInputFormDto birtBookInputFormDto, Model model){
		model.addAttribute("birtBookInputFormDto", birtBookInputFormDto);
		return "birt/iframe_birt_post";
	}
	
	@RequestMapping(value="/generate", method=RequestMethod.GET)
	public void viewIntoIframe(
			HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException, NamingException{
		String reportType = request.getParameter("reportType");
		String authorName = request.getParameter("authorName");
		String fileName = "BookJasper";
		
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("authorName", authorName);
		parameterMap.put("reportType", reportType);
		
		PrintWriter writer = response.getWriter();
		writer.print("BIRT EXAMPLE");
		
	}
	
	@RequestMapping(value="/generate", method=RequestMethod.POST)
	public String viewReport(
			@ModelAttribute("birtBookInputForm") JasperBookInputFormVO formVO,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException, NamingException{
		
		Connection conn = null;
		String reportType = request.getParameter("reportType");
		String authorName = request.getParameter("authorName");
		String fileName = "BookBirt";
		
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("authorName", authorName);
		parameterMap.put("reportType", reportType);
		
		PrintWriter writer = response.getWriter();
		writer.print("");
		return null;
	}
	
//	public void setReportEngine(IReportEngine reportEngine) {
//		this.reportEngine = reportEngine;
//	}

	public void setRenderOptions(Map renderOptions) {
		this.renderOptions = renderOptions;
	}	
	
}
