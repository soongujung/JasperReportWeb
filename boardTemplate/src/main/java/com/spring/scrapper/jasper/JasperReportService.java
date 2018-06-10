package com.spring.scrapper.jasper;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public interface JasperReportService {
	public JasperReport compileFile(String fileName, HttpServletRequest request) throws Exception;
	public void generateReportToHtml(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException;
	public void generateReportToPDF(HttpServletResponse response, Map<String, Object> parameterMap, JasperReport jasperReport, Connection conn) throws Exception;
	public HttpServletResponse generateReportToPDF(HttpServletResponse response, Map<String, Object> parameterMap, JasperReport jasperReport, Connection conn, String opt) throws Exception;
//	public void generateReprotToPDFHtml(JasperPrint jasperPrint, Map<String,Object> parameterMap, HttpServletRequest request, HttpServletResponse response)
	public void generateReportToXls(HttpServletResponse response, Map<String, Object> parameterMap, JasperPrint jasperPrint, Connection conn) throws Exception;
	public void generateReportToStream(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameterMap, Connection conn) throws Exception;
}
