package com.spring.scrapper.jasper.domain.dto;

public class JasperBookInputFormDTO {
	private String authorName;
	private String reportType;
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@Override
	public String toString() {
		return "JasperBookInputFormDTO [authorName=" + authorName + ", reportType=" + reportType + "]";
	}
}
