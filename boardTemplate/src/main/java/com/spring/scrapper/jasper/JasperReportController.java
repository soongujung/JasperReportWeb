package com.spring.scrapper.jasper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.spring.scrapper.jasper.domain.JasperBookInputFormVO;
import com.spring.scrapper.jasper.domain.dto.JasperBookInputFormDTO;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapterImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Controller
@SessionAttributes("jasperBookVO")
@RequestMapping("/jasper")
public class JasperReportController {
	
	@Inject
	private SqlSession sqlSession;
	
	@Inject
	private JasperReportService jasperService;
	
	@ModelAttribute("jasperFormatMap")
	public Map<String, String> getJasperBookFormats(){
		Map<String, String> parameterMap = new HashMap<>();
		parameterMap.put("PDF", "pdf");
		parameterMap.put("HTML", "html");
		parameterMap.put("XLS", "xls");
		return parameterMap;
	}
	
	// 그냥 심플하게 DB에 있는 데이터들의 값들을 리스트로 보고서에 출력하는걸로.
	@RequestMapping(value="/types", method=RequestMethod.GET)
	public String getType(JasperBookInputFormVO vo, ModelAndView mav){
		return "jasper/types";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getMenuList(){
		return "jasper/list";
	}
	
	@RequestMapping(value="/iframe_menu", method=RequestMethod.GET)
	public String getTypeIframe(JasperBookInputFormVO vo){
		return "jasper/iframe_menu";
	}
	
//	@RequestMapping(value="/report", method=RequestMethod.GET)
	@RequestMapping(value="/generate", method=RequestMethod.POST)
	public String viewReport(
			@ModelAttribute("jasperBookInputForm") JasperBookInputFormVO formVO,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException, NamingException{
		
//		Connection conn = sqlSession.getConnection();
		
		Connection conn = null;
//		String reportType = formVO.getReportFormat();
//		String authorName = formVO.getAuthorName();
		String reportType = request.getParameter("reportType");
		String authorName = request.getParameter("authorName");
		String fileName = "BookJasper";
		
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("authorName", authorName);
		parameterMap.put("reportType", reportType);
		
		//1) getCompiledFile(reportFileName, request) 
		try {
			JasperReport jasperReport = jasperService.compileFile(fileName, request);
			JdbcDataAdapterImpl jasperJdbc = new JdbcDataAdapterImpl();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrapper?useSSL=false&serverTimezone=UTC","scrapper","1111");
			//2) JasperPrint (-> Html or PDF)
			if("HTML".equalsIgnoreCase(reportType)){
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn1);
				jasperService.generateReportToHtml(jasperPrint, request, response);
			}
			else if("PDF".equalsIgnoreCase(reportType)){
				jasperService.generateReportToPDF(response, parameterMap, jasperReport, conn1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		return "jasper/report";
		return null;
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
		
		//1) getCompiledFile(reportFileName, request) 
		try {
			JasperReport jasperReport = jasperService.compileFile(fileName, request);
			JdbcDataAdapterImpl jasperJdbc = new JdbcDataAdapterImpl();
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrapper?useSSL=false&serverTimezone=UTC","scrapper","1111");
			//2) JasperPrint (-> Html or PDF)
			if("HTML".equalsIgnoreCase(reportType)){
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn1);
				jasperService.generateReportToHtml(jasperPrint, request, response);
			}
			else if("XLS".equalsIgnoreCase(reportType)){
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, conn1);
				JRXlsExporter exporter = new JRXlsExporter();
				String path = request.getSession().getServletContext().getRealPath("/resources/jasper/"+ fileName);
				String xlsPath = path + ".xls";
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsPath));
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(true);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				exporter.setConfiguration(configuration);
				exporter.exportReport();
			}
			else if("PDF".equalsIgnoreCase(reportType)){
				response = jasperService.generateReportToPDF(response, parameterMap, jasperReport, conn1, "");
			}
			else{
				System.out.println("hello...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/viewReport", method=RequestMethod.GET)
	public String getReportPage(){
		return "jasper/iframe_jasper";
	}
	
	@RequestMapping(value="/viewReport", method=RequestMethod.POST)
	public String getReportPagePost(JasperBookInputFormDTO jasperBookInputFormDto, Model model){
		model.addAttribute("jasperBookInputFormDto", jasperBookInputFormDto);
		return "jasper/iframe_jasper_post";
	}
}
