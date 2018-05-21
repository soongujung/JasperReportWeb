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
}
