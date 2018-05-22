package com.spring.scrapper.jasper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Service
public class JasperReportServiceImpl implements JasperReportService{

	@Override
	public JasperReport compileFile(
			String fileName, 
			HttpServletRequest request) throws Exception {
		String currPath = System.getProperty("user.dir");
		String path = request.getSession().getServletContext().getRealPath("/resources/jasper/"+ fileName);
		String jrxmlPath = path+".jrxml";
		String jasperPath = path + ".jasper";
		File reportFile = new File(jasperPath);
		
		if(!reportFile.exists()){
			JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
		}
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	}

	@Override
	public void generateReportToHtml(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response)
			throws IOException, JRException {
		HtmlExporter exporter = new HtmlExporter();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		jasperPrintList.add(jasperPrint);
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
		exporter.exportReport();
	}

	@Override
	public void generateReportToPDF(HttpServletResponse response, Map<String, Object> parameterMap,
			JasperReport jasperReport, Connection conn) throws Exception {
		byte [] binary = null;
		binary = JasperRunManager.runReportToPdf(jasperReport, parameterMap, conn);
		response.reset();
		response.resetBuffer();
		response.setContentType("application/pdf");
		response.setContentLength(binary.length);
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(binary, 0, binary.length);
		outputStream.flush();
		outputStream.close();
	}

}
