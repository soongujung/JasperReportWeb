package com.spring.scrapper.jasper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spring.scrapper.jasper.domain.BookVO;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapterImpl;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

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
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(binary, 0, binary.length);
		outputStream.flush();
		outputStream.close();
	}

	@Override
	public HttpServletResponse generateReportToPDF(HttpServletResponse response, Map<String, Object> parameterMap,
			JasperReport jasperReport, Connection conn, String opt) throws Exception {
		byte [] binary = null;
		binary = JasperRunManager.runReportToPdf(jasperReport, parameterMap, conn);
		response.reset();
		response.resetBuffer();
		response.setContentType("application/pdf");
		response.setContentLength(binary.length);
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(binary, 0, binary.length);
		outputStream.flush();
		outputStream.close();
		return response;
	}

	@Override
	public void generateReportToXls(HttpServletResponse response, Map<String, Object> parameterMap,
			JasperPrint jasperPrint, Connection conn) throws Exception {
		JRXlsExporter exporter = new JRXlsExporter();
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		jasperPrintList.add(jasperPrint);
		exporter.setExporterInput( SimpleExporterInput.getInstance(jasperPrintList) );
	}
	

	@Override
	public void generateReportToStream(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameterMap) throws Exception {
		String reportType = request.getParameter("reportType");
		String authorName = request.getParameter("authorName");
//		String fileName = "BookJasper";
		String fileName = "RptPerfDevice";

		
		parameterMap = new HashMap<>();
		parameterMap.put("authorName", authorName);
		parameterMap.put("reportType", reportType);

		BookVO vo1 = new BookVO(1, "한국축구의 역사1", 1, "박문성", 1000);
		BookVO vo2 = new BookVO(2, "한국축구의 역사2", 2, "박지성", 1001);
		BookVO vo3 = new BookVO(3, "한국축구의 역사3", 3, "서형욱", 1002);
		BookVO vo4 = new BookVO(4, "한국축구의 역사4", 4, "차범근", 1003);
		
		BookVO vo5 = new BookVO(5, "유럽축구의 역사1", 1, "박문성", 1004);
		BookVO vo6 = new BookVO(6, "유럽축구의 역사2", 2, "박지성", 1005);
		BookVO vo7 = new BookVO(7, "유럽축구의 역사3", 3, "서형욱", 1006);
		BookVO vo8 = new BookVO(8, "유럽축구의 역사4", 4, "차범근", 1007);
		List<BookVO> bookList = new ArrayList<>();
		bookList.add(vo1);
		bookList.add(vo2);
		bookList.add(vo3);
		bookList.add(vo4);
		
		List<BookVO> bookList2 = new ArrayList<>();
		bookList2.add(vo5);
		bookList2.add(vo6);
		bookList2.add(vo7);
		bookList2.add(vo8);
		
		try {
			JasperReport jasperReport = compileFile(fileName, request);
			
			JasperPrint jasperPrint = null;
			Map<String, Object> jasperParameterMap = new HashMap<String,Object>();
			JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(bookList);
			JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(bookList2);
			jasperParameterMap.put("subReport1", dataSource1);
			jasperParameterMap.put("subReport2", dataSource2);
			
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameterMap, new JREmptyDataSource());
//			jasperParameterMap 세팅 구문 작성 (SQL)
			
						
			//2) JasperPrint (-> Html or PDF)
			if("HTML".equalsIgnoreCase(reportType)){
				generateReportToHtml(jasperPrint, request, response);
			}
			else if("XLS".equalsIgnoreCase(reportType)){
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
				generateReportToPDF(response, jasperParameterMap, jasperReport, new JREmptyDataSource()); 
			}
			else{
				System.out.println("hello...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateReportToPDF(HttpServletResponse response, Map<String,Object> jasperParameterMap, JasperReport jasperReport, JRDataSource dataSource){
		byte [] binary = null;
		try {
			binary = JasperRunManager.runReportToPdf(jasperReport, jasperParameterMap, dataSource);
			response.reset();
			response.resetBuffer();
			response.setContentType("application/pdf");
			response.setContentLength(binary.length);
			response.setCharacterEncoding("UTF-8");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(binary, 0, binary.length);
			outputStream.flush();
			outputStream.close();
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
